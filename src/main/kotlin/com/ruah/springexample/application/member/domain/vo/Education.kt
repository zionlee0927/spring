package com.ruah.springexample.application.member.domain.vo

import com.ruah.springexample.global.CodeValue

enum class Education(private val description: String) : CodeValue {
    ELEMENTARY_GRADUATE("초등학교 졸업"),
    ELEMENTARY_DROPOUT("초등학교 중퇴"),
    MIDDLE_GRADUATE("중학교 졸업"),
    MIDDLE_DROPOUT("중학교 중퇴"),
    HIGH_GRADUATE("고등학교 졸업"),
    HIGH_DROPOUT("고등학교 중퇴"),
    COLLEGE_GRADUATE("대학교 졸업"),
    COLLEGE_DROPOUT("대학교 중퇴"),
    GRADUATE("대학원 이상");

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}