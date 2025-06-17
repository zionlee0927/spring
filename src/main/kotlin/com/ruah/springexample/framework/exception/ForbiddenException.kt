package com.ruah.springexample.framework.exception

class ForbiddenException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
) : ApplicationException(error, description, throwable){

    constructor() : this(ErrorCode.FORBIDDEN, ErrorCode.FORBIDDEN.getDescription())
    constructor(error: ErrorCode) : this(error, error.getDescription())
    constructor(description: String) : this(ErrorCode.FORBIDDEN, description)
}