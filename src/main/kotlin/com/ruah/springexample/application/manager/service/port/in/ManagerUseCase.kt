package com.ruah.springexample.application.manager.service.port.`in`

import com.ruah.springexample.application.manager.service.port.dto.ManagerDto

interface ManagerUseCase {

    fun signUp(command: CreateManagerCommand): ManagerDto

    fun get(id: String): ManagerDto

    fun edit(id: String, command: EditManagerCommand): ManagerDto

    fun changePassword(id: String, command: ChangePasswordManagerCommand)

    fun getAccount(id: String): String

    fun search(command: SearchManagerCommand): List<ManagerDto>

    fun withdraw(id: String)
}