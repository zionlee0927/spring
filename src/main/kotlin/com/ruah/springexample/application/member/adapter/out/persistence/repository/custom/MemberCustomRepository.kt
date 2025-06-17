package com.ruah.springexample.application.member.adapter.out.persistence.repository.custom

import com.ruah.springexample.application.member.service.port.dto.MemberSearchDto
import com.ruah.springexample.application.member.service.port.`in`.command.SearchMemberCommand
import com.ruah.springexample.framework.wrapper.PageResponse

interface MemberCustomRepository {

    fun search(command: SearchMemberCommand) : PageResponse<MemberSearchDto>
}