package com.ruah.springexample.application.member.service

import com.ruah.springexample.application.member.domain.Member
import com.ruah.springexample.application.member.service.converter.MemberConverter
import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.application.member.service.port.dto.MemberSearchDto
import com.ruah.springexample.application.member.service.port.`in`.MemberUseCase
import com.ruah.springexample.application.member.service.port.`in`.command.ChangePasswordMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.EditMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.RegisterMemberCommand
import com.ruah.springexample.application.member.service.port.`in`.command.SearchMemberCommand
import com.ruah.springexample.application.member.service.port.out.MemberPort
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode
import com.ruah.springexample.framework.wrapper.PageResponse
import org.springframework.stereotype.Service
import kotlin.text.get

@Service
class MemberService(
    private val port: MemberPort,
    private val converter: MemberConverter
) : MemberUseCase {
    override fun register(command: RegisterMemberCommand): MemberDto {
        validRegister(command)
        val member = Member(command)
        val dto = converter.toDto(member)
        return port.save(dto)
    }

    override fun get(memberId: String): MemberDto {
        return port.get(memberId)
    }

    override fun edit(memberId: String, command: EditMemberCommand): MemberDto {
        val memberDto = port.get(memberId)
        validEdit(command, memberDto)
        val member = converter.toDomain(memberDto)
        val editedMember = member.edit(command)
        val editedMemberDto = converter.toDto(editedMember)

        return port.save(editedMemberDto)
    }

    override fun changePassword(memberId: String, command: ChangePasswordMemberCommand) {
        val memberDto = port.get(memberId)
        val member = converter.toDomain(memberDto)
        val editedMember = member.changePassword(command)
        val editedMemberDto = converter.toDto(editedMember)
        port.save(editedMemberDto)
    }

    override fun delete(memberId: String) {
        val memberDto = port.get(memberId)
        val member = converter.toDomain(memberDto)
        val deleted = member.delete()
        val deletedMemberDto = converter.toDto(deleted)

        port.save(deletedMemberDto)
    }

    private fun validRegister(command: RegisterMemberCommand) {
        validAccount(command.account)
        validPhone(command.phone)
    }

    private fun validEdit(command: EditMemberCommand, memberDto: MemberDto) {
        if (memberDto.account != command.account) {
            validAccount(command.account)
        }
        if (memberDto.phone != command.phone) {
            validPhone(command.phone)
        }
    }

    private fun validAccount(account: String) {
        if (port.existsByAccount(account)) {
            throw BadRequestException(ErrorCode.DUPLICATE_ACCOUNT)
        }
    }

    private fun validPhone(phone: String) {
        if (port.existsByPhone(phone)) {
            throw BadRequestException(ErrorCode.DUPLICATE_PHONE)
        }
    }
}