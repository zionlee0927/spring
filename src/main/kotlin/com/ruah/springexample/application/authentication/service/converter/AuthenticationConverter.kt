package com.ruah.springexample.application.authentication.service.converter

import com.ruah.springexample.application.authentication.domain.Authentication
import com.ruah.springexample.application.authentication.service.port.dto.AuthenticationDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AuthenticationConverter {

    fun toDto(domain: Authentication) : AuthenticationDto
    fun toDomain(dto: AuthenticationDto) : Authentication
}