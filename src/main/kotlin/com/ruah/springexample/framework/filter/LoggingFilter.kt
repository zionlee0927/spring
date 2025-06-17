package com.ruah.springexample.framework.filter

import com.ruah.springexample.framework.filter.ApiInfoMessage
import com.ruah.springexample.framework.filter.HttpLogMessage
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.LogManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper


@Component
class LoggingFilter : OncePerRequestFilter() {
    private val log = LogManager.getLogger("CUSTOM_JSON_LAYOUT_LOGGER");

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cachingRequestWrapper = ContentCachingRequestWrapper(request)
        val cachingResponseWrapper = ContentCachingResponseWrapper(response)

        val startTime = System.currentTimeMillis()
        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper)
        val end = System.currentTimeMillis()

        val userDetails = SecurityContextHolder.getContext().authentication?.principal as? UserDetails

        try {
            val apiInfoMessage = HttpLogMessage.create(
                    requestWrapper = cachingRequestWrapper,
                    responseWrapper = cachingResponseWrapper,
                    elapsedTime = (end - startTime) / 1000.0,
                    userDetails = userDetails
                )

            log.info(ApiInfoMessage(apiInfoMessage))

            cachingResponseWrapper.copyBodyToResponse()
        } catch (e: Exception) {
            log.error("[${this::class.simpleName}] Logging 실패")
        }
    }
}