package com.ruah.springexample.framework.config.security.jwt

import com.ruah.springexample.framework.exception.ErrorCode
import com.ruah.springexample.framework.util.JsonUtil
import com.ruah.springexample.framework.wrapper.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {
    private val logger = LoggerFactory.getLogger(JwtAccessDeniedHandler::class.java)

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        logger.debug("Jwt Access Denied. Message - ${accessDeniedException.message}")
        setErrorResponse(response, accessDeniedException)
    }

    private fun setErrorResponse(response: HttpServletResponse, exception: AccessDeniedException) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        val errorResponse = ErrorResponse(ErrorCode.FORBIDDEN, exception.message!!)
        response.writer.write(JsonUtil.generateClassToJson(errorResponse)!!)
    }
}
