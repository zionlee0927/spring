package com.ruah.springexample.application.authentication.adapter.out.persistence.entity

import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "authentication")
class AuthenticationEntity(
    @Id
    val id: String,

    @Enumerated(EnumType.STRING)
    var platformType: AuthenticationPlatformType,

    @Column
    var target: String,

    @Column
    var token: String?,

    @Column
    var number: String,

    @Column
    var createdAt: LocalDateTime
)