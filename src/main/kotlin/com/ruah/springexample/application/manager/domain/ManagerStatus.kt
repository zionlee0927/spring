package com.ruah.springexample.application.manager.domain

import com.ruah.springexample.global.CodeValue

enum class ManagerStatus(private val description: String) : CodeValue {
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