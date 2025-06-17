package com.ruah.springexample.framework.exception

class BadRequestException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
) : ApplicationException(error, description, throwable){

    constructor() : this(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getDescription())
    constructor(error: ErrorCode) : this(error, error.getDescription())
    constructor(description: String) : this(ErrorCode.BAD_REQUEST, description)
}