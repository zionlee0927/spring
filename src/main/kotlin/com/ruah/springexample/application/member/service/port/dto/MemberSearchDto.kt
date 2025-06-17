package com.ruah.springexample.application.member.service.port.dto

import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.application.member.domain.vo.MemberStatus
import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class MemberSearchDto @QueryProjection constructor(
    val id: String,
    val status: MemberStatus,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val gender: Gender,
    val education: Education,
)
