package com.ruah.springexample.application.member.service.converter

import com.ruah.springexample.application.member.domain.vo.MemberPassword
import org.mapstruct.Mapper
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface MemberVoConverter {

    @Named("toMemberPassword")
    fun toMemberPassword(value: String): MemberPassword
}