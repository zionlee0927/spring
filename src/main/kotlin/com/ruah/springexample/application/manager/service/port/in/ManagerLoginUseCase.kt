package com.ruah.springexample.application.manager.service.port.`in`

import com.ruah.springexample.framework.config.security.dto.TokenResponse

interface ManagerLoginUseCase {

    fun login(command: ManagerLoginCommand): TokenResponse
    fun refresh(id: String): String
}