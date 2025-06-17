package com.ruah.springexample.application.manager.service.port.`in`

import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode

data class EditManagerCommand(
    val account: String,
    val name: String,
    val email: String,
    val phone: String
) {
    init {
        validName()
    }

    private fun validName() {
        val namePattern = """^[가-힣]{2,10}""".toRegex()
        if (!namePattern.matches(name)) {
            throw BadRequestException(ErrorCode.INVALID_NAME)
        }
    }
}
