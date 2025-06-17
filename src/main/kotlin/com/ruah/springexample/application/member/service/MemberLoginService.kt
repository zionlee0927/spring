package com.ruah.springexample.application.member.service

import com.ruah.springexample.application.member.service.converter.MemberConverter
import com.ruah.springexample.application.member.service.port.`in`.MemberLoginUseCase
import com.ruah.springexample.application.member.service.port.`in`.command.MemberLoginCommand
import com.ruah.springexample.application.member.service.port.out.MemberPort
import com.ruah.springexample.framework.config.security.dto.TokenResponse
import com.ruah.springexample.framework.config.security.dto.UserType
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.exception.BadRequestException
import org.springframework.stereotype.Service

@Service
class MemberLoginService(
    private val port: MemberPort,
    private val converter: MemberConverter,
    private val jwtUtil: JwtUtil,
): MemberLoginUseCase {
    val userType = UserType.MEMBER

    override fun login(command: MemberLoginCommand): TokenResponse {
        val dto = port.getByAccount(command.account)

        val member = converter.toDomain(dto)

        if (!member.status.enabled()) {
            throw BadRequestException("로그인 가능한 상태가 아님")
        }

        if (!member.matchPassword(command.password)) {
            throw BadRequestException("인증 실패")
        }

        val tokenInfo = converter.toTokenInfo(member, userType)
        val jwt = jwtUtil.generateMemberToken(tokenInfo)
        return TokenResponse(
            accessToken = jwt.first,
            refreshToken = jwt.second
        )
    }

    override fun refresh(id: String): String {
        val dto = port.get(id)
        val member = converter.toDomain(dto)
        val tokenInfo = converter.toTokenInfo(member, userType)
        val jwt = jwtUtil.generateMemberToken(tokenInfo)
        return jwt.first
    }
}