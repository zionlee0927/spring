package com.ruah.springexample.application.manager.service.port.dto

import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerStatus
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.application.manager.domain.vo.ManagerPassword
import java.time.LocalDateTime

data class ManagerDto(
    val id: String,
    val account: String,
    val password: ManagerPassword,
    val name: String,
    val status: ManagerStatus,
    val email: String,
    val phone: String,
    val roles: List<ManagerRole>,
    val type: ManagerType,
    val createdAt: LocalDateTime
)
