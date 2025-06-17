package com.ruah.springexample.application.member.service.port.`in`.command

import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode
import java.time.LocalDate

data class EditMemberCommand(
    val account: String,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val protectorPhone: String,
    val gender: Gender,
    val education: Education,
    val memo: String,
    val managerId: String
) {
    init {
        validAccount()
        validName()
        validContract()
    }

    private fun validAccount() {
        val accountPattern = """^[가-힣0-9]{1,8}""".toRegex()
        if (!accountPattern.matches(account)) {
            throw BadRequestException(ErrorCode.BAD_REQUEST, "Invalid account")
        }
    }

    private fun validName() {
        val namePattern = """^[가-힣a-zA-Z]{2,12}""".toRegex()
        if (!namePattern.matches(name)) {
            throw BadRequestException(ErrorCode.BAD_REQUEST, "Invalid name")
        }
    }

    private fun validContract() {
        val phonePattern = """^010-[0-9]{4}-[0-9]{4}""".toRegex()
        if (!phonePattern.matches(phone)) {
            throw BadRequestException(ErrorCode.BAD_REQUEST, "Invalid phone number")
        }
    }
}
