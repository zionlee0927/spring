package com.ruah.springexample.application.member.service.port.`in`

import com.ruah.springexample.application.member.service.port.`in`.command.MemberLoginCommand
import com.ruah.springexample.framework.config.security.dto.TokenResponse

interface MemberLoginUseCase {

    fun login(command: MemberLoginCommand): TokenResponse

    fun refresh(id: String): String
}