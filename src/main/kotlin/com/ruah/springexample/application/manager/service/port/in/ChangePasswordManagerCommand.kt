package com.ruah.springexample.application.manager.service.port.`in`

import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode

data class ChangePasswordManagerCommand(
    val password: String,
    val checkPassword: String
) {
    init {
        validPassword()
    }

    private fun validPassword() {

        if (password != checkPassword) {
            throw BadRequestException(ErrorCode.NOT_MATCHED_TWO_PASSWORD)
        }
        // $@$!%*#?&.

        val passwordPattern = """^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,20}$""".toRegex()
        if (!passwordPattern.matches(password)) {
            throw BadRequestException(ErrorCode.INVALID_PASSWORD)
        }
    }
}
