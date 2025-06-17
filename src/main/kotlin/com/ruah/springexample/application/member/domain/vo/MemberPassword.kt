package com.ruah.springexample.application.member.domain.vo

import com.ruah.springexample.framework.config.mapstruct.Default
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode
import com.ruah.springexample.framework.util.EncryptUtil
import com.ruah.springexample.framework.wrapper.vo.SingleVo

class MemberPassword @Default constructor(
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

    private fun valid(password: String, checkPassword: String) {

        if (password != checkPassword) {
            throw BadRequestException(ErrorCode.NOT_MATCHED_TWO_PASSWORD)
        }

        val passwordPattern = """[0-9]{1,8}""".toRegex()
        if (!passwordPattern.matches(password)) {
            throw BadRequestException(ErrorCode.INVALID_PASSWORD)
        }
    }
}
