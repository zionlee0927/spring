package com.ruah.springexample.application.manager.adapter.out.persistence.dao

import com.ruah.springexample.application.manager.adapter.out.persistence.repository.ManagerRepository
import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import com.ruah.springexample.application.manager.service.port.`in`.SearchManagerCommand
import com.ruah.springexample.application.manager.service.port.out.ManagerPort
import com.ruah.springexample.application.manager.adapter.out.converter.ManagerEntityConverter
import com.ruah.springexample.framework.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManagerDao(
    private val repository: ManagerRepository,
    private val converter: ManagerEntityConverter
) : ManagerPort {

    @Transactional
    override fun save(managerDto: ManagerDto): ManagerDto {
        val entity = converter.toEntity(managerDto)
        val saved = repository.save(entity)
        return converter.toDto(saved)
    }

    @Transactional(readOnly = true)
    override fun get(id: String): ManagerDto {
        val entity = repository.findByIdOrNull(id) ?: throw NotFoundException("Manager 정보 없음. id - $id")
        return converter.toDto(entity)
    }

    @Transactional(readOnly = true)
    override fun search(command: SearchManagerCommand): List<ManagerDto> {
        val entityList = repository.search(command)
        return converter.toDto(entityList)
    }

    @Transactional(readOnly = true)
    override fun existsByAccount(account: String): Boolean {
        return repository.existsByAccount(account)
    }

    @Transactional(readOnly = true)
    override fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email)
    }

    @Transactional(readOnly = true)
    override fun existsByPhone(phone: String): Boolean {
        return repository.existsByPhone(phone)
    }
}