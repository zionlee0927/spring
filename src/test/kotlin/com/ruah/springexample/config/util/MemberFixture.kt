package com.ruah.springexample.config.util

import com.ruah.springexample.application.member.adapter.out.persistence.entity.MemberEntity
import com.ruah.springexample.application.member.domain.Member
import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.application.member.domain.vo.MemberPassword
import com.ruah.springexample.application.member.domain.vo.MemberStatus
import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.config.TestConstant.Companion.TEST_ENCRYPTED_PASSWORD
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_ACCOUNT
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_BIRTHDAY
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_EDUCATION
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_GENDER
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_NAME
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_PHONE
import com.ruah.springexample.config.TestConstant.Companion.TEST_MEMBER_STATUS
import com.ruah.springexample.framework.config.security.dto.TokenInfo
import com.ruah.springexample.framework.config.security.dto.UserType
import java.time.LocalDate
import java.time.LocalDateTime

fun makeMember(
    id: String = TEST_MEMBER_ID,
    account: String = TEST_MEMBER_ACCOUNT,
    password: String = TEST_ENCRYPTED_PASSWORD,
    name: String = TEST_MEMBER_NAME,
    status: MemberStatus = TEST_MEMBER_STATUS,
    phone: String = TEST_MEMBER_PHONE,
    birthday: LocalDate = LocalDate.now(),
    protectorPhone: String = TEST_MEMBER_PHONE,
    gender: Gender = TEST_MEMBER_GENDER,
    education: Education = TEST_MEMBER_EDUCATION,
    memo: String = "memo",
    createdAt: LocalDateTime = LocalDateTime.now(),
    managerId: String = TEST_MANAGER_ID
): Member {
    return Member(
        id = id,
        account = account,
        password = MemberPassword(password),
        name = name,
        status = status,
        phone = phone,
        birthday = birthday,
        protectorPhone = protectorPhone,
        gender = gender,
        education = education,
        memo = memo,
        createdAt = createdAt,
        managerId = managerId
    )
}
fun makeMemberDto(
    id: String = TEST_MEMBER_ID,
    account: String = TEST_MEMBER_ACCOUNT,
    password: String = TEST_ENCRYPTED_PASSWORD,
    name: String = TEST_MEMBER_NAME,
    status: MemberStatus = TEST_MEMBER_STATUS,
    phone: String = TEST_MEMBER_PHONE,
    birthday: LocalDate = LocalDate.now(),
    protectorPhone: String = TEST_MEMBER_PHONE,
    gender: Gender = TEST_MEMBER_GENDER,
    education: Education = TEST_MEMBER_EDUCATION,
    memo: String = "memo",
    createdAt: LocalDateTime = LocalDateTime.now(),
    managerId: String = TEST_MANAGER_ID
): MemberDto {
    return MemberDto(
        id = id,
        account = account,
        password = password,
        name = name,
        status = status,
        phone = phone,
        birthday = birthday,
        protectorPhone = protectorPhone,
        gender = gender,
        education = education,
        memo = memo,
        createdAt = createdAt,
        managerId = managerId
    )
}

fun makeMemberEntity(
    id: String = TEST_MEMBER_ID,
    account: String = TEST_MEMBER_ACCOUNT,
    password: String = TEST_ENCRYPTED_PASSWORD,
    name: String = TEST_MEMBER_NAME,
    status: MemberStatus = TEST_MEMBER_STATUS,
    phone: String = TEST_MEMBER_PHONE,
    birthday: LocalDate = TEST_MEMBER_BIRTHDAY,
    protectorPhone: String = TEST_MEMBER_PHONE,
    gender: Gender = TEST_MEMBER_GENDER,
    education: Education = TEST_MEMBER_EDUCATION,
    memo: String = "memo",
    createdAt: LocalDateTime = LocalDateTime.now(),
    managerId: String = TEST_MANAGER_ID
) : MemberEntity {
    return MemberEntity(
        id = id,
        account = account,
        password = password,
        name = name,
        status = status,
        phone = phone,
        birthday = birthday,
        protectorPhone = protectorPhone,
        gender = gender,
        education = education,
        memo = memo,
        createdAt = createdAt,
        managerId = managerId
    )
}

fun makeMemberTokenInfo(
    id: String = TEST_MEMBER_ID,
    name: String = TEST_MEMBER_NAME,
    userType: UserType = UserType.MEMBER
): TokenInfo {
    return TokenInfo(
        id = id,
        name = name,
        userType = userType,
        roles = userType.getRoles()
    )
}
