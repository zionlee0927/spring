package com.ruah.springexample.application.authentication.service.port.out

import com.ruah.springexample.application.authentication.service.port.dto.AuthenticationDto

interface AuthenticationPort {

    fun save(dto: AuthenticationDto) : AuthenticationDto
    fun get(id: String) : AuthenticationDto
}