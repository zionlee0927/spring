package com.ruah.springexample.framework.config.security.jwt

import com.ruah.springexample.framework.exception.ErrorCode
import com.ruah.springexample.framework.util.JsonUtil
import com.ruah.springexample.framework.wrapper.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.nio.charset.StandardCharsets

class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint::class.java)

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.debug("Failed Auth Message - ${authException.message}")
        setErrorResponse(response, authException)
    }

    private fun setErrorResponse(response: HttpServletResponse, exception: AuthenticationException) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        val errorResponse = ErrorResponse(ErrorCode.UNAUTHORIZED, exception.message!!)
        response.writer.write(JsonUtil.generateClassToJson(errorResponse)!!)
    }
}
