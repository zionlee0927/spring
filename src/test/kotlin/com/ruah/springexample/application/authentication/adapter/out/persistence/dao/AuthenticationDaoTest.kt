package com.ruah.springexample.application.authentication.adapter.out.persistence.dao

import com.ruah.springexample.application.authentication.adapter.out.converter.AuthenticationEntityConverter
import com.ruah.springexample.application.authentication.adapter.out.persistence.repository.AuthenticationRepository
import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.service.port.dto.AuthenticationDto
import com.ruah.springexample.config.TestConstant
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ID
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime

class AuthenticationDaoTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val repository = mockk<AuthenticationRepository>()
    val converter = mockk<AuthenticationEntityConverter>(relaxed = true)
    val dao = AuthenticationDao(repository, converter)

    describe("Save AuthenticationNumber") {
        context("Save in Database") {
            val dto = AuthenticationDto(
                id = TEST_MANAGER_ID,
                platformType = AuthenticationPlatformType.PHONE,
                target = TestConstant.TEST_MANAGER_PHONE,
                number = "123456",
                createdAt = LocalDateTime.now(),
                token = null
            )

            every { repository.save(any()) } returns mockk()

            it("Call Repository") {
                dao.save(dto)

                verify(exactly = 1) {repository.save(any())}
            }
        }
    }
})