package com.ruah.springexample.framework.config.security.provider

import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.config.security.token.FrontPreProcessingToken
import com.ruah.springexample.framework.exception.NotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class MemberTokenProvider(
    private val jwtUtil: JwtUtil
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val jwtToken = authentication.principal as String
        val memberInfo = jwtUtil.getMemberInfo(jwtToken)

        return try {
            UsernamePasswordAuthenticationToken(memberInfo, null, memberInfo.authorities)
        } catch (e: NotFoundException) {
            UsernamePasswordAuthenticationToken(memberInfo, null, memberInfo.authorities)
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(FrontPreProcessingToken::class.java)
    }
}
