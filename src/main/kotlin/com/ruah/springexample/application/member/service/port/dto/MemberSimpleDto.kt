package com.ruah.springexample.application.member.service.port.dto

import com.ruah.springexample.application.member.domain.vo.Gender
import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class MemberSimpleDto @QueryProjection constructor(
    val id: String,
    val name: String,
    val gender: Gender,
    val birthday: LocalDate
)