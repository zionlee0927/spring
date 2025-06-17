package com.ruah.springexample.application.member.service.port.`in`.command

data class MemberLoginCommand(
    val account: String,
    val password: String
)
