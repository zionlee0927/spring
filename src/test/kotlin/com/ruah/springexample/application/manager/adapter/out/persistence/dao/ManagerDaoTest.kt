package com.ruah.springexample.application.manager.adapter.out.persistence.dao

import com.ruah.springexample.application.manager.adapter.out.converter.ManagerEntityConverter
import com.ruah.springexample.application.manager.adapter.out.persistence.repository.ManagerRepository
import com.ruah.springexample.application.manager.domain.ManagerStatus
import com.ruah.springexample.application.manager.service.port.`in`.SearchManagerCommand
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_EMAIL
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_PHONE
import com.ruah.springexample.config.util.makeManagerDto
import com.ruah.springexample.framework.exception.NotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.repository.findByIdOrNull

class ManagerDaoTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val repository = mockk<ManagerRepository>()
    val converter = mockk<ManagerEntityConverter>(relaxed = true)
    val dao = ManagerDao(repository, converter)

    describe("매니저 정보 생성") {
        context("정상적으로 요청되면") {
            val managerDto = makeManagerDto()

            every { repository.save(any()) } returns mockk()

            it("Database 에 Save 를 요청한다.") {
                dao.save(managerDto)

                verify(exactly = 1) { repository.save(any()) }
            }
        }
    }

    describe("아이디로 매니저 조회") {
        context("등록된 매니저 아이디로 요청하면") {
            every {repository.findByIdOrNull(any()) } returns mockk()
            it("Database 에서 조회한다.") {
                dao.get(TEST_MANAGER_ID)

                verify(exactly = 1) { repository.findByIdOrNull(any()) }
            }
        }

        context("등록되지 않은 매니저로 요청하면") {
            every {repository.findByIdOrNull(any()) } returns null
            it("익셉션이 발생한다.") {
                shouldThrow<NotFoundException> { dao.get("NO_SIGNUP_MANGER_ID") }
            }
        }
    }

    describe("매니저 검색") {
        context("매니저 관련 정보를 검색 요청을 하면") {
            val command = SearchManagerCommand(
                status = ManagerStatus.ACTIVE
            )

            every { repository.search(command) } returns mockk()
            it("Database 에서 검색한다.") {
                dao.search(command)

                verify(exactly = 1) { repository.search(any()) }
            }
        }
    }

    describe("이메일 기등록 여부") {
        context("동일한 이메일이 등록되어 있는지 확인") {
            val email = TEST_MANAGER_EMAIL

            every { repository.existsByEmail(any()) } returns false
            it("이메일이 있으면 True, 없으면 False") {
                dao.existsByEmail(email)

                verify(exactly = 1) { repository.existsByEmail(any()) }
            }
        }
    }

    describe("연락처 기등록 여부") {
        context("동일한 연락처가 등록되어 있는지 확인") {
            val phone = TEST_MANAGER_PHONE

            every { repository.existsByPhone(any()) } returns false
            it("연락처가 있으면 True, 없으면 False") {
                dao.existsByPhone(phone)

                verify(exactly = 1) { repository.existsByPhone(any()) }
            }
        }
    }

})