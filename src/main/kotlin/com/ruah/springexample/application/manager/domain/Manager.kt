package com.ruah.springexample.application.manager.domain

import com.ruah.springexample.application.manager.service.port.`in`.ChangePasswordManagerCommand
import com.ruah.springexample.application.manager.service.port.`in`.CreateManagerCommand
import com.ruah.springexample.application.manager.service.port.`in`.EditManagerCommand
import com.ruah.springexample.application.manager.domain.vo.ManagerPassword
import com.ruah.springexample.framework.config.mapstruct.Default
import ulid.ULID
import java.time.LocalDateTime

class Manager @Default constructor(
    val id: String,
    val account: String,
    val name: String,
    val password: ManagerPassword,
    val status: ManagerStatus,
    val email: String,
    val phone: String,
    val roles: List<ManagerRole>,
    val type: ManagerType,
    val createdAt: LocalDateTime
) {

    constructor(command: CreateManagerCommand): this(
        id = ULID.randomULID(),
        account = command.account,
        password = ManagerPassword(command.account+"1234", command.account+"1234"),
        name = command.name,
        status = ManagerStatus.ACTIVE,
        email = command.email,
        phone = command.phone,
        roles = command.roles,
        type = command.type,
        createdAt = LocalDateTime.now()
    )

    fun edit(command: EditManagerCommand): Manager {
        return Manager(
            id = this.id,
            account = this.account,
            password = this.password,
            status = this.status,
            roles = this.roles,
            type = this.type,
            createdAt = this.createdAt,
            name = command.name,
            email = command.email,
            phone = command.phone
        )
    }

    fun changePassword(command: ChangePasswordManagerCommand): Manager {
        return Manager(
            id = this.id,
            account = this.account,
            status = this.status,
            name = this.name,
            email = this.email,
            phone = this.phone,
            roles = this.roles,
            type = this.type,
            createdAt = this.createdAt,

            password = this.password.change(command.password, command.checkPassword)
        )
    }

    fun matchPassword(source: String): Boolean {
        return password.match(source)
    }

    fun withdraw() : Manager {
        return Manager(
            id = this.id,
            account = this.account,
            password = this.password,
            name = this.name,
            email = this.email,
            phone = this.phone,
            roles = this.roles,
            type = this.type,
            createdAt = this.createdAt,

            status = ManagerStatus.WITHDRAW
        )
    }
}