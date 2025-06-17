package com.ruah.springexample.framework.advice

import com.ruah.springexample.framework.exception.*
import com.ruah.springexample.framework.wrapper.ErrorResponse
import com.ruah.springexample.logger
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException


@RestControllerAdvice
class ControllerExceptionAdvice {
    val log = logger()

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(exception: BadRequestException): ErrorResponse {
        return ErrorResponse(exception.error, exception.description)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(exception: NotFoundException): ErrorResponse {
        return ErrorResponse(exception.error, exception.description)
    }

    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnauthorized(exception: UnauthorizedException): ErrorResponse {
        return ErrorResponse(exception.error, exception.description)
    }

    @ExceptionHandler(ForbiddenException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleForbidden(exception: ForbiddenException): ErrorResponse {
        return ErrorResponse(exception.error, exception.description)
    }

    @ExceptionHandler(ApplicationException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleBeauBrainException(exception: ApplicationException): ErrorResponse {
        log.error(exception.stackTraceToString())
        return ErrorResponse(ErrorCode.BEAUBRAIN_EXCEPTION, exception.message!!)
    }

    @ExceptionHandler(RuntimeException::class, Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception): ErrorResponse {
        log.error(exception.stackTraceToString())
        return ErrorResponse(ErrorCode.BEAUBRAIN_EXCEPTION, exception.message!!)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadable(exception: HttpMessageNotReadableException): ErrorResponse {
        return ErrorResponse(ErrorCode.BAD_REQUEST, exception.message!!)
    }

    @ExceptionHandler(NoHandlerFoundException::class, HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleHttpRequestMethodNotSupportedException(): ErrorResponse {
        return ErrorResponse(ErrorCode.URL_NOT_FOUND, ErrorCode.URL_NOT_FOUND.getDescription())
    }
}