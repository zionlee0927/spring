package com.ruah.springexample.application.authentication.adapter.out.persistence.repository

import com.ruah.springexample.config.DataJpaTestInContainer
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import makeAuthenticationEntity
import org.springframework.beans.factory.annotation.Autowired

@DataJpaTestInContainer
class AuthenticationRepositoryTest @Autowired constructor(
    private val repository: AuthenticationRepository
) : DescribeSpec({

    describe("Save") {
        context("AuthenticationNumber Entity") {
            val entity = makeAuthenticationEntity()

            it("Save") {
                val saved = repository.save(entity)
                saved shouldNotBe null
            }
        }
    }
})