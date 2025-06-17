package com.ruah.springexample.framework.config.security.provider

import com.ruah.springexample.framework.config.security.dto.InternalDetails
import com.ruah.springexample.framework.config.security.token.InternalPreProcessingToken
import com.ruah.springexample.framework.exception.ForbiddenException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class InternalTokenProvider(
    private val tokenInfo: String,
): AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication.principal as String

        if (token != tokenInfo) {
            throw ForbiddenException("Internal is disabled")
        }

        val internalDetails = InternalDetails(
            id = "INTERNAL",
            name = "INTERNAL",
        )

        return UsernamePasswordAuthenticationToken(internalDetails, null, internalDetails.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication.isAssignableFrom(InternalPreProcessingToken::class.java)
    }
}
