package com.ruah.springexample.application.authentication.domain

import com.ruah.springexample.global.CodeValue

enum class AuthenticationUserType(private val description: String) : CodeValue {
    MANAGER("매니저"),
    MEMBER("회원");

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}