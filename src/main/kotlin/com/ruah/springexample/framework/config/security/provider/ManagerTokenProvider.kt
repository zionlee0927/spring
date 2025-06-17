package com.ruah.springexample.framework.config.security.provider

import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.config.security.token.ManagerPreProcessingToken
import com.ruah.springexample.framework.exception.NotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class ManagerTokenProvider(
    private val jwtUtil: JwtUtil
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val jwtToken = authentication.principal as String
        val managerDetails = jwtUtil.getManagerInfo(jwtToken)

        return try {
            UsernamePasswordAuthenticationToken(managerDetails, null, managerDetails.authorities)
        } catch (e: NotFoundException) {
            UsernamePasswordAuthenticationToken(managerDetails, null, managerDetails.authorities)
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(ManagerPreProcessingToken::class.java)
    }
}
