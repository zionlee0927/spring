package com.ruah.springexample.application.manager.adapter.out.persistence.repository

import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.adapter.out.persistence.repository.custom.ManagerCustomRepository
import com.ruah.springexample.framework.config.jpa.DefaultJpaRepository
import org.springframework.stereotype.Repository

interface ManagerRepository : DefaultJpaRepository<ManagerEntity, String>, ManagerCustomRepository {

    fun existsByAccount(account: String): Boolean
    fun existsByEmail(email: String): Boolean
    fun existsByPhone(phone: String): Boolean
}