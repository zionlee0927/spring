package com.ruah.springexample.application.authentication.domain

import com.ruah.springexample.global.CodeValue

enum class AuthenticationPlatformType(private val description: String) : CodeValue {
    EMAIL("이메일"),
    PHONE("연락처");

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}