package com.ruah.springexample.application.member.service.port.`in`

import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.application.member.service.port.dto.MemberSearchDto
import com.ruah.springexample.application.member.service.port.`in`.command.ChangePasswordMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.EditMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.RegisterMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.SearchMemberCommand
import com.ruah.springexample.framework.wrapper.PageResponse

interface MemberUseCase {

    fun register(command: RegisterMemberCommand): MemberDto
    fun get(memberId: String): MemberDto
    fun edit(memberId: String, command: EditMemberCommand): MemberDto
    fun changePassword(memberId: String, command: ChangePasswordMemberCommand)
    fun delete(memberId: String)
}