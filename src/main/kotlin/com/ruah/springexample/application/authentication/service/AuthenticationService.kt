package com.ruah.springexample.application.authentication.service

import com.ruah.springexample.application.manager.service.port.`in`.ManagerUseCase
import com.ruah.springexample.application.manager.service.port.`in`.SearchManagerCommand
import com.ruah.springexample.application.authentication.domain.Authentication
import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.domain.AuthenticationUserType
import com.ruah.springexample.application.authentication.service.converter.AuthenticationConverter
import com.ruah.springexample.application.authentication.service.port.`in`.AuthenticationUseCase
import com.ruah.springexample.application.authentication.service.port.`in`.IssueAuthenticationNumberCommand
import com.ruah.springexample.application.authentication.service.port.`in`.IssueTemporaryTokenCommand
import com.ruah.springexample.application.authentication.service.port.out.AuthenticationPort
import com.ruah.springexample.application.authentication.service.port.out.SendAuthenticationPort
import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.framework.config.security.dto.TokenInfo
import com.ruah.springexample.framework.config.security.dto.UserType
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val port: AuthenticationPort,
    private val sendPort: List<SendAuthenticationPort>,
    private val managerUseCase: ManagerUseCase,
    private val converter: AuthenticationConverter,
    private val jwtUtil: JwtUtil
) : AuthenticationUseCase {
    companion object {
        const val TEMPORARY_TOKEN_EXPIRE_TIME = 10 * 60 * 1000L // 10분
    }
    override fun issueAuthenticationNumber(command: IssueAuthenticationNumberCommand): String {
        val id = getUserId(
            userType = command.userType,
            platformType = command.platformType,
            target = command.target,
            name = command.name
        )

        val authentication = Authentication(id, command)
        val dto = converter.toDto(authentication)

        port.save(dto)
        sendPort
            .first { port -> port.type == command.platformType }
            .send(command.target)

        return authentication.number
    }

    @Transactional
    override fun issueTemporaryToken(command: IssueTemporaryTokenCommand): String {
        val id = getUserId(
            userType = command.userType,
            platformType = command.platformType,
            target = command.target,
            name = command.name
        )

        val dto = port.get(id)
        val authentication = converter.toDomain(dto)
        authentication.verify(command.number)

        val tempTokenInfo = TokenInfo(
            id = id,
            name = String(),
            roles = arrayOf(ManagerRole.ROLE_CENTER_ACCESS.name),
            userType = UserType.valueOf(ManagerType.MANAGER.name)
        )

        val token = jwtUtil.generateManagerToken(tempTokenInfo, TEMPORARY_TOKEN_EXPIRE_TIME)
        val issuedTokenAuthentication = authentication.token(token)
        val issuedTokenDto = converter.toDto(issuedTokenAuthentication)
        port.save(issuedTokenDto)

        return token
    }

    private fun getUserId(
        userType: AuthenticationUserType,
        platformType: AuthenticationPlatformType,
        target: String,
        name: String
    ) : String {
        val searchManagerCommand = when (platformType) {
            AuthenticationPlatformType.EMAIL -> SearchManagerCommand(email = target, name = name)
            AuthenticationPlatformType.PHONE -> SearchManagerCommand(phone = target, name = name)
        }

        val searchResult = managerUseCase.search(searchManagerCommand)
        if (searchResult.isEmpty()) {
            throw NotFoundException("해당 계정 정보가 없습니다.")
        }

        return searchResult.first().id
    }
}