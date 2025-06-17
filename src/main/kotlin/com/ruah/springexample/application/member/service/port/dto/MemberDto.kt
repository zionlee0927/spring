package com.ruah.springexample.application.member.service.port.dto

import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.application.member.domain.vo.MemberStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class MemberDto(
    val id: String,
    val account: String,
    val status: MemberStatus,
    val password: String,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val protectorPhone: String,
    val managerId: String,
    val gender: Gender,
    val education: Education,
    val memo: String,
    val createdAt: LocalDateTime
)
