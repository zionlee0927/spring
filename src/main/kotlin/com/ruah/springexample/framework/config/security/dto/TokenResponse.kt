package com.ruah.springexample.framework.config.security.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
