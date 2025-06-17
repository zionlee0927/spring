package com.ruah.springexample.application.authentication.domain

import com.ruah.springexample.application.authentication.service.port.`in`.IssueAuthenticationNumberCommand
import com.ruah.springexample.framework.config.mapstruct.Default
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.util.HashUtil
import java.time.LocalDateTime

class Authentication @Default constructor(
    val id: String,
    val platformType: AuthenticationPlatformType,
    val target: String,
    val number: String,
    val createdAt: LocalDateTime,
    var token: String? = null
) {
    companion object {
        private const val NUMBER_LENGTH = 6
    }

    constructor(id: String, command: IssueAuthenticationNumberCommand): this(
        id = id,
        platformType = command.platformType,
        target = command.target,
        number = HashUtil.randomNumber(NUMBER_LENGTH),
        createdAt = LocalDateTime.now()
    )

    fun token(temporaryToken: String): Authentication {
        return Authentication(
            id = this.id,
            platformType = this.platformType,
            target = this.target,
            number = this.number,
            createdAt = this.createdAt,
            token = temporaryToken
        )
    }

    fun verify(enteredNumber: String) {
        if (this.number != enteredNumber) {
            throw BadRequestException("인증 실패")
        }

        if (createdAt.plusMinutes(5).isBefore(LocalDateTime.now())) {
            throw BadRequestException("인증 실패")
        }

    }
}