package com.ruah.springexample.application.authentication.adapter.out.persistence.repository

import com.ruah.springexample.application.authentication.adapter.out.persistence.entity.AuthenticationEntity
import com.ruah.springexample.framework.config.jpa.DefaultJpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthenticationRepository : DefaultJpaRepository<AuthenticationEntity, String> {
}