package com.ruah.springexample.application.authentication.adapter.out.converter

import com.ruah.springexample.application.authentication.adapter.out.persistence.entity.AuthenticationEntity
import com.ruah.springexample.application.authentication.service.port.dto.AuthenticationDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AuthenticationEntityConverter {

    fun toEntity(dto: AuthenticationDto): AuthenticationEntity
    fun toDto(entity: AuthenticationEntity): AuthenticationDto
}