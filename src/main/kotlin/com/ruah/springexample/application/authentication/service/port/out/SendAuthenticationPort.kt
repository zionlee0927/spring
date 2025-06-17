package com.ruah.springexample.application.authentication.service.port.out

import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType

interface SendAuthenticationPort {

    val type: AuthenticationPlatformType
    fun send(target: String)
}