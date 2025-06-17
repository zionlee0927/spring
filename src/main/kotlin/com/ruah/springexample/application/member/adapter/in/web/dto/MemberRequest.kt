package com.ruah.springexample.application.member.adapter.`in`.web.dto

import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import java.time.LocalDate

data class RegisterMemberRequest(
    val account: String,
    val password: String,
    val checkPassword: String,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val protectorPhone: String,
    val gender: Gender,
    val education: Education,
    val memo: String
)

data class EditMemberRequest(
    val account: String,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val protectorPhone: String,
    val gender: Gender,
    val education: Education,
    val memo: String
)


data class ChangePasswordMemberRequest(
    val password: String,
    val checkPassword: String
)

data class MemberLoginRequest(
    val account: String,
    val password: String
)
