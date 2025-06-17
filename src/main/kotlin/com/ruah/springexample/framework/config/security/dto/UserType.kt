package com.ruah.springexample.framework.config.security.dto

import com.ruah.springexample.global.CodeValue


enum class UserType(private val roles: Array<String>, private val description: String) : CodeValue {
    GUEST(arrayOf("ROLE_GUEST"), "게스트"),
    MEMBER(arrayOf("ROLE_MEMBER"), "멤버"),
    MANAGER(arrayOf(), "매니저"),
    MASTER(arrayOf(), "마스터"),
    INTERNAL(arrayOf("ROLE_INTERNAL"), "인터널"),
    ADMIN(arrayOf("ROLE_ADMIN"), "관리자");

    fun getRoles(): Array<String> {
        return roles
    }
    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}