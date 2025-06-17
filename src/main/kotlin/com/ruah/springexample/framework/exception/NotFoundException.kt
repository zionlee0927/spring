package com.ruah.springexample.framework.exception

class NotFoundException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
) : ApplicationException(error, description, throwable){

    constructor() : this(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getDescription())
    constructor(error: ErrorCode) : this(error, error.getDescription())
    constructor(description: String) : this(ErrorCode.NOT_FOUND, description)
}