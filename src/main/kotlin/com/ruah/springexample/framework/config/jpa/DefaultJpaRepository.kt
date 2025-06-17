package com.ruah.springexample.framework.config.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface DefaultJpaRepository<E, I : Serializable> : JpaRepository<E, I>, JpaSpecificationExecutor<E> {

}