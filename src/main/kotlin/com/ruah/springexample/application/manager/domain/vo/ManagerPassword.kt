package com.ruah.springexample.application.manager.domain.vo

import com.ruah.springexample.framework.config.mapstruct.Default
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode
import com.ruah.springexample.framework.util.EncryptUtil
import com.ruah.springexample.framework.wrapper.vo.SingleVo

class ManagerPassword @Default constructor(
    override val value: String
) : SingleVo<String> {

    constructor(password: String, checkPassword: String): this(
        value = EncryptUtil.encryptByArgon2(password)
    ) {
        valid(password, checkPassword)
    }

    fun match(password: String): Boolean {
        return EncryptUtil.matchByArgon2(password, value)
    }

    fun change(password: String, checkPassword: String): ManagerPassword {
        valid(password, checkPassword)
        return ManagerPassword(
            value = EncryptUtil.encryptByArgon2(password)
        )
    }

    private fun valid(password: String, checkPassword: String) {

        if (password != checkPassword) {
            throw BadRequestException(ErrorCode.NOT_MATCHED_TWO_PASSWORD)
        }

        val passwordPattern = """^[a-zA-Z0-9!@#${'$'}%^&*()]{4,20}""".toRegex()
        if (!passwordPattern.matches(password)) {
            throw BadRequestException(ErrorCode.INVALID_PASSWORD)
        }
    }
}
