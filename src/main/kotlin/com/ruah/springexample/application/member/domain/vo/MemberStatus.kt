package com.ruah.springexample.application.member.domain.vo

import com.ruah.springexample.global.CodeValue

enum class MemberStatus(private val description: String) : CodeValue {
    ACTIVE("활성"),
    WITHDRAW("탈퇴");

    fun enabled(): Boolean {
        return this == ACTIVE
    }

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}