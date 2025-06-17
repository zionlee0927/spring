package com.ruah.springexample.application.manager.service.port.out

import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import com.ruah.springexample.application.manager.service.port.`in`.SearchManagerCommand

interface ManagerPort {

    fun save(managerDto: ManagerDto): ManagerDto

    fun get(id: String): ManagerDto

    fun search(command: SearchManagerCommand): List<ManagerDto>

    fun existsByAccount(account: String): Boolean
    fun existsByEmail(email: String): Boolean
    fun existsByPhone(phone: String): Boolean
}