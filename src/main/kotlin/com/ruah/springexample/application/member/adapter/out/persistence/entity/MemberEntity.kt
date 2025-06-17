package com.ruah.springexample.application.member.adapter.out.persistence.entity

import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.application.member.domain.vo.MemberStatus
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    val id: String,
    @Column
    val managerId: String,
    @Column
    val account: String,
    @Column
    val password: String,
    @Enumerated(EnumType.STRING)
    val status: MemberStatus,
    @Column
    val name: String,
    @Column
    val birthday: LocalDate,
    @Enumerated(EnumType.STRING)
    val gender: Gender,
    @Enumerated(EnumType.STRING)
    val education: Education,
    @Column
    val phone: String,
    @Column
    val protectorPhone: String,
    @Column
    val memo: String?,
    @Column
    val createdAt: LocalDateTime
) {

}