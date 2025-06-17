package com.ruah.springexample.framework.exception

open class ApplicationException(
    val error: ErrorCode,
    val description: String,
    throwable: Throwable?
) : RuntimeException(error.name, throwable) {
    constructor(error: ErrorCode) : this(error, error.getDescription(), null)
    constructor(description: String) : this(ErrorCode.BAD_REQUEST, description, null)
}
