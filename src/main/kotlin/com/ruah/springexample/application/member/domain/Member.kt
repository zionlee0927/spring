package com.ruah.springexample.application.member.domain

import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.application.member.domain.vo.MemberPassword
import com.ruah.springexample.application.member.domain.vo.MemberStatus
import com.ruah.springexample.application.member.service.port.`in`.command.ChangePasswordMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.EditMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.RegisterMemberCommand
import com.ruah.springexample.framework.config.mapstruct.Default
import ulid.ULID
import java.time.LocalDate
import java.time.LocalDateTime

class Member @Default constructor(
    val id: String,
    val managerId: String,
    val account: String,
    val status: MemberStatus,
    val password: MemberPassword,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val protectorPhone: String,
    val gender: Gender,
    val education: Education,
    val memo: String? = null,
    val createdAt: LocalDateTime
) {

    constructor(command: RegisterMemberCommand): this(
        id = ULID.randomULID(),
        managerId = command.managerId,
        account = command.account,
        status = MemberStatus.ACTIVE,
        password = MemberPassword(command.password, command.checkPassword),
        name = command.name,
        phone = command.phone,
        birthday = command.birthday,
        protectorPhone = command.protectorPhone,
        gender = command.gender,
        education = command.education,
        memo = command.memo,
        createdAt = LocalDateTime.now()
    )

    fun edit(command: EditMemberCommand): Member {
        return Member(
            id = this.id,
            status = this.status,
            password = this.password,
            createdAt = this.createdAt,
            managerId = this.managerId,

            account = command.account,
            name = command.name,
            phone = command.phone,
            birthday = command.birthday,
            protectorPhone = command.protectorPhone,
            gender = command.gender,
            education = command.education,
            memo = command.memo
        )
    }


    fun delete(): Member {
        return Member(
            id = this.id,
            account = this.account,
            password = this.password,
            createdAt = this.createdAt,
            name = this.name,
            phone = this.phone,
            birthday = this.birthday,
            protectorPhone = this.protectorPhone,
            gender = this.gender,
            education = this.education,
            memo = this.memo,
            managerId = this.managerId,

            status = MemberStatus.WITHDRAW
        )
    }

    fun changePassword(command: ChangePasswordMemberCommand): Member {
        return Member(
            id = this.id,
            account = this.account,
            createdAt = this.createdAt,
            name = this.name,
            phone = this.phone,
            birthday = this.birthday,
            protectorPhone = this.protectorPhone,
            gender = this.gender,
            education = this.education,
            memo = this.memo,
            status = this.status,
            managerId = this.managerId,

            password = MemberPassword(password = command.password, checkPassword = command.checkPassword),
        )

    }

    fun matchPassword(source: String): Boolean {
        return password.match(source)
    }
}