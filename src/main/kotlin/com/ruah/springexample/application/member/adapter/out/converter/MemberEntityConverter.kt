package com.ruah.springexample.application.member.adapter.out.converter

import com.ruah.springexample.application.member.adapter.out.persistence.entity.MemberEntity
import com.ruah.springexample.application.member.service.port.dto.MemberDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface MemberEntityConverter {

    fun toEntity(dto: MemberDto): MemberEntity
    fun toDto(entity: MemberEntity): MemberDto
    fun toDto(entity: List<MemberEntity>): List<MemberDto>
}