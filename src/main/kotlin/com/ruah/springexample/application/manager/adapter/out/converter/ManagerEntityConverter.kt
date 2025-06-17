package com.ruah.springexample.application.manager.adapter.out.converter

import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.service.converter.ManagerVoConverter
import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.Named

@Mapper(componentModel = "spring", uses = [ManagerVoConverter::class])
abstract class ManagerEntityConverter {
    companion object {
        private const val SEPARATOR = "//"
    }

    @Mappings(
        Mapping(source = "roles", target = "roles", qualifiedByName = ["stringToRoles"]),
        Mapping(source = "entity", target = "password", qualifiedByName = ["toManagerPassword"])
    )
    abstract fun toDto(entity: ManagerEntity): ManagerDto
    abstract fun toDto(entity: List<ManagerEntity>): List<ManagerDto>

    @Mappings(
        Mapping(source = "roles", target = "roles", qualifiedByName = ["rolesToString"]),
        Mapping(source = "password.value", target = "password"),
    )
    abstract fun toEntity(dto: ManagerDto): ManagerEntity

    @Named("rolesToString")
    fun rolesToString(roles: List<ManagerRole>): String {
        return roles.joinToString(separator = SEPARATOR) { it.getName() }
    }

    @Named("stringToRoles")
    fun stringToRoles(roles: String): List<ManagerRole> {
        return roles.split(SEPARATOR).map { ManagerRole.valueOf(it) }
    }
}