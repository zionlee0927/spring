package com.ruah.springexample.application.authentication.adapter.`in`.web.dto

import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.domain.AuthenticationUserType

data class IssueAuthenticationNumberRequest(
    val userType: AuthenticationUserType,
    val name: String,
    val platformType: AuthenticationPlatformType,
    val target: String
)

data class IssueTemporaryTokenRequest(
    val userType: AuthenticationUserType,
    val name: String,
    val platformType: AuthenticationPlatformType,
    val target: String,
    val number: String
)