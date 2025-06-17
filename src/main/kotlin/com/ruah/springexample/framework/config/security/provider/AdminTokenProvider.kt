package com.ruah.springexample.framework.config.security.provider

import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.config.security.token.AdminPreProcessingToken
import com.ruah.springexample.framework.exception.NotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class AdminTokenProvider(
    private val jwtUtil: JwtUtil
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val jwtToken = authentication.principal as String
        val adminDetails = jwtUtil.getAdminInfo(jwtToken)

        return try {
            UsernamePasswordAuthenticationToken(adminDetails, null, adminDetails.authorities)
        } catch (e: NotFoundException) {
            UsernamePasswordAuthenticationToken(adminDetails, null, adminDetails.authorities)
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(AdminPreProcessingToken::class.java)
    }
}
