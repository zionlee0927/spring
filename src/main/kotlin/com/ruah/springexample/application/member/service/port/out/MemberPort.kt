package com.ruah.springexample.application.member.service.port.out

import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.application.member.service.port.dto.MemberSearchDto
import com.ruah.springexample.application.member.service.port.`in`.command.SearchMemberCommand
import com.ruah.springexample.framework.wrapper.PageResponse

interface MemberPort {

    fun save(dto: MemberDto): MemberDto
    fun search(command: SearchMemberCommand): PageResponse<MemberSearchDto>
    fun get(memberId: String): MemberDto
    fun getByAccount(account: String): MemberDto
    fun existsByAccount(account: String): Boolean
    fun existsByPhone(phone: String): Boolean
}