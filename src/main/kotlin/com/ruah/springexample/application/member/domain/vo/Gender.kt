package com.ruah.springexample.application.member.domain.vo

import com.ruah.springexample.global.CodeValue

enum class Gender(private val description: String) : CodeValue {
    MALE("남성"),
    FEMALE("여성");

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}