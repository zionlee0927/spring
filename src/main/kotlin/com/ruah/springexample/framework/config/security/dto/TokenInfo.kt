package com.ruah.springexample.framework.config.security.dto

open class TokenInfo(
    val id: String,
    val name: String,
    val userType: UserType,
    val roles: Array<String>
)