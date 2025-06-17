package com.ruah.springexample.application.authentication.service.port.dto

import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import java.time.LocalDateTime

data class AuthenticationDto(
    val id: String,
    val platformType: AuthenticationPlatformType,
    val target: String,
    val number: String,
    val createdAt: LocalDateTime,
    val token: String?
)
