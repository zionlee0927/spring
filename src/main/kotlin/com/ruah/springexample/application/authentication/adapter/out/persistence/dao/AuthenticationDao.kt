package com.ruah.springexample.application.authentication.adapter.out.persistence.dao

import com.ruah.springexample.application.authentication.adapter.out.converter.AuthenticationEntityConverter
import com.ruah.springexample.application.authentication.adapter.out.persistence.repository.AuthenticationRepository
import com.ruah.springexample.application.authentication.service.port.dto.AuthenticationDto
import com.ruah.springexample.application.authentication.service.port.out.AuthenticationPort
import com.ruah.springexample.framework.config.jpa.ReadOnlyTransactional
import com.ruah.springexample.framework.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthenticationDao(
    private val repository: AuthenticationRepository,
    private val converter: AuthenticationEntityConverter
) : AuthenticationPort {
    override fun save(dto: AuthenticationDto): AuthenticationDto {
        val entity = converter.toEntity(dto)
        val saved = repository.save(entity)
        return converter.toDto(saved)
    }

    @ReadOnlyTransactional
    override fun get(id: String): AuthenticationDto {
        val entity = repository.findByIdOrNull(id)
            ?: let { throw NotFoundException("인증 정보 찾을 수 없음") }

        return converter.toDto(entity)
    }
}