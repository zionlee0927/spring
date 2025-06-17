package com.ruah.springexample.application.manager.adapter.out.persistence.repository

import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerStatus
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.config.DataJpaTestInContainer
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import ulid.ULID
import java.time.LocalDateTime

@DataJpaTestInContainer
class ManagerRepositoryTest @Autowired constructor(
    private val repository: ManagerRepository
) : DescribeSpec({

    describe("ManagerRepository") {
        context("Save Manager Entity") {
            val entity = ManagerEntity(
                id = ULID.randomULID(),
                account = "manager",
                password = "password1",
                name = "매니저",
                status = ManagerStatus.ACTIVE,
                email = "email@email.com",
                phone = "01234567890",
                roles = ManagerRole.ROLE_CENTER_ACCESS.getName(),
                type = ManagerType.MANAGER,
                createdAt = LocalDateTime.now()
            )

            it("Saved in Database") {
                val manager = repository.save(entity)
                manager shouldNotBe null
            }
        }
    }

    describe("Manager Repository") {
        context("findById Manager Entity") {
            val entity = ManagerEntity(
                id = ULID.randomULID(),
                account = "manager",
                password = "password1",
                name = "매니저",
                status = ManagerStatus.ACTIVE,
                email = "email@email.com",
                phone = "01234567890",
                roles = ManagerRole.ROLE_CENTER_ACCESS.getName(),
                type = ManagerType.MANAGER,
                createdAt = LocalDateTime.now()
            )

            val saved = repository.save(entity)

            it("Search Database") {
                val manager = repository.findByIdOrNull(saved.id)
                manager shouldNotBe null
            }
        }
    }


})