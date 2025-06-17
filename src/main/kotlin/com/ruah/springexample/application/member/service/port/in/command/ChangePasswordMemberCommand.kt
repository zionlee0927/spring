package com.ruah.springexample.application.member.service.port.`in`.command

data class ChangePasswordMemberCommand(
    val password: String,
    val checkPassword: String
)
