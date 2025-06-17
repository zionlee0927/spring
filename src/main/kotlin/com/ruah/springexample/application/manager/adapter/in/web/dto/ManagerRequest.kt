package com.ruah.springexample.application.manager.adapter.`in`.web.dto

data class CreateManagerRequest(
    val account: String,
    val name: String,
    val email: String,
    val phone: String
)

data class EditManagerRequest(
    val account: String,
    val name: String,
    val email: String,
    val phone: String
)

data class ChangePasswordManagerRequest(
    val password: String,
    val checkPassword: String
)

data class ManagerLoginRequest(
    val account: String,
    val password: String
)