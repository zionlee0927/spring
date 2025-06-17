package com.ruah.springexample.application.manager.adapter.`in`.converter

import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import com.ruah.springexample.application.manager.service.port.`in`.ChangePasswordManagerCommand
import com.ruah.springexample.application.manager.service.port.`in`.CreateManagerCommand
import com.ruah.springexample.application.manager.service.port.`in`.EditManagerCommand
import com.ruah.springexample.application.manager.service.port.`in`.ManagerLoginCommand
import com.ruah.springexample.application.manager.adapter.`in`.web.dto.*
import com.ruah.springexample.application.manager.domain.ManagerType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ManagerCommandConverter {

    @Mappings(
        Mapping(target = "account", source = "request.account"),
        Mapping(target = "name", source = "request.name"),
        Mapping(target = "email", source = "request.email"),
        Mapping(target = "phone", source = "request.phone"),
        Mapping(target = "roles", expression = "java(List.of())"),
        Mapping(target = "type", source = "type")
    )
    fun toCommand(request: CreateManagerRequest, type: ManagerType): CreateManagerCommand
    fun toCommand(request: EditManagerRequest): EditManagerCommand
    fun toCommand(request: ChangePasswordManagerRequest): ChangePasswordManagerCommand
    fun toCommand(request: ManagerLoginRequest): ManagerLoginCommand

    fun toResponse(managerDto: ManagerDto): ManagerResponse
}