package com.ruah.springexample.application.manager.service.converter

import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.domain.vo.ManagerPassword
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface ManagerVoConverter {


    @Named("toManagerPassword")
    @Mappings(
        Mapping(source = "password", target = "value"),
    )
    fun toManagerPassword(entity: ManagerEntity): ManagerPassword
}