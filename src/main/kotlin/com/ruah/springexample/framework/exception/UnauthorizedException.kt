package com.ruah.springexample.framework.exception

import com.ruah.springexample.framework.exception.ApplicationException
import com.ruah.springexample.framework.exception.ErrorCode

class UnauthorizedException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
) : ApplicationException(error, description, throwable){

    constructor() : this(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getDescription())
    constructor(error: ErrorCode) : this(error, error.getDescription())
    constructor(description: String) : this(ErrorCode.UNAUTHORIZED, description)
}