package com.ruah.springexample.application.member.service.converter

import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.application.member.domain.Member
import com.ruah.springexample.framework.config.mapstruct.SingleValueConverter
import com.ruah.springexample.framework.config.security.dto.TokenInfo
import com.ruah.springexample.framework.config.security.dto.UserType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [SingleValueConverter::class, MemberVoConverter::class])
abstract class MemberConverter {

    @Mappings(
        Mapping(source = "password", target = "password", qualifiedByName = ["toMemberPassword"]),
        Mapping(target = "edit", ignore = true)
    )
    abstract fun toDomain(dto: MemberDto): Member

    @Mappings(
        Mapping(source = "password", target = "password",  qualifiedByName = ["objectToValue"])
    )
    abstract fun toDto(domain: Member): MemberDto

    @Mappings(
        Mapping(target = "id", source = "member.id"),
        Mapping(target = "name", source = "member.name"),
        Mapping(target = "userType", source = "userType"),
    )
    abstract fun toTokenInfo(member: Member, userType: UserType): TokenInfo

}

