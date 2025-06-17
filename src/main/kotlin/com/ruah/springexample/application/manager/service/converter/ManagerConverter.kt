package com.ruah.springexample.application.manager.service.converter

import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import com.ruah.springexample.application.manager.domain.Manager
import com.ruah.springexample.framework.config.mapstruct.SingleValueConverter
import com.ruah.springexample.framework.config.security.dto.TokenInfo
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface ManagerConverter {

    fun toDomain(dto: ManagerDto): Manager

    fun toDto(manager: Manager): ManagerDto

    @Mappings(
        Mapping(target = "userType", source = "type"),
    )
    fun toTokenInfo(manager: Manager): TokenInfo

}