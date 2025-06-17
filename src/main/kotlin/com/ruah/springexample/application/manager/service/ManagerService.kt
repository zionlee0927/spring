package com.ruah.springexample.application.manager.service

import com.ruah.springexample.application.manager.domain.Manager
import com.ruah.springexample.application.manager.service.converter.ManagerConverter
import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import com.ruah.springexample.application.manager.service.port.`in`.*
import com.ruah.springexample.application.manager.service.port.out.ManagerPort
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManagerService(
    private val port: ManagerPort,
    private val converter: ManagerConverter
) : ManagerUseCase {

    @Transactional
    override fun signUp(command: CreateManagerCommand): ManagerDto {
        validSignUp(command)
        val manager = Manager(command)
        val dto = converter.toDto(manager)
        return port.save(dto)
    }

    override fun get(id: String): ManagerDto {
        return port.get(id)
    }

    override fun edit(id: String, command: EditManagerCommand): ManagerDto {
        validEdit(command)
        val dto = port.get(id)
        val manager = converter.toDomain(dto)
        val editedManager = manager.edit(command)
        val editedDto = converter.toDto(editedManager)
        return port.save(editedDto)
    }

    override fun changePassword(id: String, command: ChangePasswordManagerCommand) {
        val dto = port.get(id)
        val manager = converter.toDomain(dto)
        val edited = manager.changePassword(command)
        val editedDto = converter.toDto(edited)
        port.save(editedDto)
    }

    override fun getAccount(id: String): String {
        val dto = port.get(id)
        val manager = converter.toDomain(dto)
        return manager.account
    }


    override fun search(command: SearchManagerCommand): List<ManagerDto> {
        return port.search(command)
    }

    override fun withdraw(id: String) {
        val dto = port.get(id)
        val manager = converter.toDomain(dto)
        val withdrawManager = manager.withdraw()
        val editedDto = converter.toDto(withdrawManager)
        port.save(editedDto)
    }

    private fun validSignUp(command: CreateManagerCommand) {
        validAccount(command.account)
        validEmail(command.email)
        validPhone(command.phone)
    }

    private fun validEdit(command: EditManagerCommand) {
        validEmail(command.email)
        validPhone(command.phone)
    }

    private fun validAccount(account: String) {
        if (port.existsByAccount(account)) {
            throw BadRequestException(ErrorCode.DUPLICATE_ACCOUNT)
        }
    }

    private fun validEmail(email: String) {
        if (port.existsByEmail(email)) {
            throw BadRequestException(ErrorCode.DUPLICATE_EMAIL)
        }
    }

    private fun validPhone(phone: String) {
        if (port.existsByPhone(phone)) {
            throw BadRequestException(ErrorCode.DUPLICATE_PHONE)
        }
    }
}