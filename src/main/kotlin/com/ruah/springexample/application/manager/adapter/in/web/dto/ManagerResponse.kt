package com.ruah.springexample.application.manager.adapter.`in`.web.dto

import com.ruah.springexample.application.manager.domain.ManagerType
import java.time.LocalDateTime

data class ManagerResponse(
    val id: String,
    val account: String,
    val name: String,
    val email: String,
    val phone: String,
    val type: ManagerType,
    val createdAt: LocalDateTime
)