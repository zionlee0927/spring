package com.ruah.springexample.framework.filter

import com.ruah.springexample.framework.config.security.dto.AdminDetails
import com.ruah.springexample.framework.config.security.dto.ManagerDetails
import com.ruah.springexample.framework.config.security.dto.MemberDetails
import com.ruah.springexample.framework.config.security.dto.UserType
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

data class HttpLogMessage(
    val api: String,
    val status: HttpStatus,
    val elapsedTime: Double,
    val userId: String?,
    val userType: List<UserType>,
    val requestBody: String?,
    val responseBody: String?,
) {
    companion object {
        fun create(
            requestWrapper: ContentCachingRequestWrapper,
            responseWrapper: ContentCachingResponseWrapper,
            elapsedTime: Double,
            userDetails: UserDetails?
        ): HttpLogMessage {
            val userType: UserType? = getUserType(userDetails)
            val status = HttpStatus.valueOf(responseWrapper.status)
            return HttpLogMessage(
                api = "${requestWrapper.method} ${requestWrapper.requestURI}${toPrettierRequestParam(requestWrapper.parameterMap)}",
                status = status,
                elapsedTime = elapsedTime,
                userId = userDetails?.username,
                userType = userType?.let { listOf(it) } ?: emptyList(),
                requestBody = if (!status.is2xxSuccessful) { String(requestWrapper.contentAsByteArray) } else { null },
                responseBody = if (status.is5xxServerError) { String(responseWrapper.contentAsByteArray) } else { null }
            )
        }

        private fun getUserType(userDetails: UserDetails?) = when (userDetails) {
            is MemberDetails -> userDetails.type
            is ManagerDetails -> userDetails.type
            is AdminDetails -> userDetails.type
            else -> UserType.GUEST
        }

        private fun toPrettierRequestParam(requestParam: Map<String, Array<String>>): String {
            return if (requestParam.isNotEmpty()) {
                "?" + requestParam.entries.joinToString("&") { "${it.key}=${it.value.joinToString()}" }
            } else {
                ""
            }
        }
    }
}
