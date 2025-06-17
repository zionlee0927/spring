package com.ruah.springexample.application.authentication.service.port.`in`

import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.domain.AuthenticationUserType

data class IssueAuthenticationNumberCommand(
    val userType: AuthenticationUserType,
    val name: String,
    val platformType: AuthenticationPlatformType,
    val target: String
)
