package com.ruah.springexample.application.member.adapter.`in`.converter

import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.application.member.service.port.`in`.command.ChangePasswordMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.EditMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.MemberLoginCommand
import com.ruah.springexample.application.member.service.port.`in`.command.RegisterMemberCommand
import com.ruah.springexample.application.member.adapter.`in`.web.dto.*
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface MemberCommandConverter {

    fun toResponse(dto: MemberDto): MemberResponse
    fun toCommand(request: RegisterMemberRequest): RegisterMemberCommand
    fun toCommand(request: EditMemberRequest): EditMemberCommand
    fun toCommand(request: MemberLoginRequest): MemberLoginCommand
    fun toCommand(request: ChangePasswordMemberRequest): ChangePasswordMemberCommand
}