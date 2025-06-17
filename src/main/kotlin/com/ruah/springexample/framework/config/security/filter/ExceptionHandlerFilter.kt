package com.ruah.springexample.framework.config.security.filter

import com.ruah.springexample.framework.util.JsonUtil
import com.ruah.springexample.framework.wrapper.ErrorResponse
import com.ruah.springexample.framework.exception.*
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class ExceptionHandlerFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BadRequestException) {
            setBadRequestException(response, e)
        } catch (e: UnauthorizedException) {
            setUnauthorizedException(response, e)
        } catch (e: ForbiddenException) {
            setForbiddenException(response, e)
        } catch (e: NotFoundException) {
            setNotFoundException(response, e)
        } catch (e: RuntimeException) {
            setException(response, e)
        } catch (e: Exception) {
            setException(response, e)
        }
    }

    private fun setBadRequestException(response: HttpServletResponse, exception: BadRequestException) {
        setErrorResponse(response, HttpStatus.BAD_REQUEST, ErrorResponse(exception.error, exception.description))
    }

    private fun setUnauthorizedException(response: HttpServletResponse, exception: UnauthorizedException) {
        setErrorResponse(response, HttpStatus.UNAUTHORIZED, ErrorResponse(exception.error, exception.description))
    }

    private fun setForbiddenException(response: HttpServletResponse, exception: ForbiddenException) {
        setErrorResponse(response, HttpStatus.FORBIDDEN, ErrorResponse(exception.error, exception.description))
    }

    private fun setNotFoundException(response: HttpServletResponse, exception: NotFoundException) {
        setErrorResponse(response, HttpStatus.NOT_FOUND, ErrorResponse(exception.error, exception.description))
    }

    private fun setException(response: HttpServletResponse, exception: Exception) {
        setErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponse(ErrorCode.BEAUBRAIN_EXCEPTION, exception.message!!))
    }

    private fun setErrorResponse(response: HttpServletResponse, status: HttpStatus, errorResponse: ErrorResponse) {
        response.status = status.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(JsonUtil.generateClassToJson(errorResponse)!!)
    }
}
