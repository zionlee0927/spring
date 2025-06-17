package com.ruah.springexample.framework.wrapper

import com.ruah.springexample.framework.exception.ErrorCode

data class ErrorResponse(val error: ErrorCode, val description: String)