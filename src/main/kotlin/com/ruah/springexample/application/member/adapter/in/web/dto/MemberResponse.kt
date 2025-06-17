package com.ruah.springexample.application.member.adapter.`in`.web.dto

import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.application.member.domain.vo.MemberStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class MemberResponse(
    val id: String,
    val account: String,
    val status: MemberStatus,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val protectorPhone: String,
    val gender: Gender,
    val education: Education,
    val memo: String,
    val createdAt: LocalDateTime
)