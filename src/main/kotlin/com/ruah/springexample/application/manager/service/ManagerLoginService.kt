package com.ruah.springexample.application.manager.service

import com.ruah.springexample.application.manager.service.converter.ManagerConverter
import com.ruah.springexample.application.manager.service.port.`in`.ManagerLoginCommand
import com.ruah.springexample.application.manager.service.port.`in`.ManagerLoginUseCase
import com.ruah.springexample.application.manager.service.port.`in`.SearchManagerCommand
import com.ruah.springexample.application.manager.service.port.out.ManagerPort
import com.ruah.springexample.framework.config.security.dto.TokenResponse
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.exception.BadRequestException
import org.springframework.stereotype.Service

@Service
class ManagerLoginService(
    private val port: ManagerPort,
    private val converter: ManagerConverter,
    private val jwtUtil: JwtUtil
) : ManagerLoginUseCase {
    override fun login(command: ManagerLoginCommand): TokenResponse {
        val searchCommand = SearchManagerCommand(
            account = command.account
        )
        val searchResult = port.search(searchCommand)
        if (searchResult.isEmpty()) {
            throw BadRequestException("인증 실패")
        }

        val dto = searchResult.first()
        val manager = converter.toDomain(dto)

        if (!manager.status.enabled()) {
            throw BadRequestException("로그인 가능한 상태가 아님")
        }

        if (!manager.matchPassword(command.password)) {
            throw BadRequestException("인증 실패")
        }

        val tokenInfo = converter.toTokenInfo(manager)
        val jwt = jwtUtil.generateManagerToken(tokenInfo)
        return TokenResponse(
            accessToken = jwt.first,
            refreshToken = jwt.second
        )
    }

    override fun refresh(id: String): String {
        val dto = port.get(id)
        val manager = converter.toDomain(dto)
        val tokenInfo = converter.toTokenInfo(manager)
        val jwt = jwtUtil.generateManagerToken(tokenInfo)
        return jwt.first
    }
}