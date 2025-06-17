package com.ruah.springexample.framework.config.security.filter

import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.config.security.token.AdminPreProcessingToken
import com.ruah.springexample.framework.config.security.token.FrontPreProcessingToken
import com.ruah.springexample.framework.config.security.token.InternalPreProcessingToken
import com.ruah.springexample.framework.config.security.token.ManagerPreProcessingToken
import com.ruah.springexample.framework.exception.BadRequestException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

@Order(4)
class CustomAuthenticationFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationToken = getToken(request)

        val token = when (request.requestURI.split("/")[1]) {
            "api" -> FrontPreProcessingToken(authorizationToken, authorizationToken.length)
            "center" -> ManagerPreProcessingToken(authorizationToken, authorizationToken.length)
            "admin" -> AdminPreProcessingToken(authorizationToken, authorizationToken.length)
            "common" -> commonToken(authorizationToken)
            "internal" -> InternalPreProcessingToken(authorizationToken, authorizationToken.length)
            else -> {
                throw BadRequestException()
            }
        }
        val authentication = authenticationManager.authenticate(token)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun commonToken(authorizationToken: String): UsernamePasswordAuthenticationToken {
        return when {
            jwtUtil.getClaim(authorizationToken).contains("MANAGER") -> ManagerPreProcessingToken(authorizationToken, authorizationToken.length)
            jwtUtil.getClaim(authorizationToken).contains("MEMBER") -> FrontPreProcessingToken(authorizationToken, authorizationToken.length)
            jwtUtil.getClaim(authorizationToken).contains("ADMIN") -> AdminPreProcessingToken(authorizationToken, authorizationToken.length)
            else -> throw BadRequestException()
        }
    }

    private fun getToken(request: HttpServletRequest): String {
        val headerPrefix = "Bearer "
        val tokenPayload = request.getHeader("Authorization")
        if (tokenPayload.isNullOrEmpty() || tokenPayload.length <= headerPrefix.length) {
            return ""
        }
        return tokenPayload.substring(headerPrefix.length, tokenPayload.length)
    }
}