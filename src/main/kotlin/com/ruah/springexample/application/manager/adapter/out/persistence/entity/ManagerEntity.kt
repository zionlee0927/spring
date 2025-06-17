package com.ruah.springexample.application.manager.adapter.out.persistence.entity

import com.ruah.springexample.application.manager.domain.ManagerStatus
import com.ruah.springexample.application.manager.domain.ManagerType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "manager")
class ManagerEntity(
    @Id
    val id: String,

    @Column
    val account: String,

    @Column
    val password: String,

    @Enumerated(EnumType.STRING)
    val status: ManagerStatus,

    @Column
    val name: String,

    @Column
    val email: String,

    @Column
    val phone: String,

    @Column
    val roles: String,

    @Enumerated(EnumType.STRING)
    val type: ManagerType,

    @Column
    val createdAt: LocalDateTime
)