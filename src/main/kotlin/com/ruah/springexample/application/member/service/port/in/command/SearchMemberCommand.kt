package com.ruah.springexample.application.member.service.port.`in`.command

import com.ruah.springexample.framework.wrapper.PageRequest

data class SearchMemberCommand(
    val account: String? = null,
    val name: String? = null,
    val page: PageRequest
)
