package com.ruah.springexample.application.manager.service

import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.application.manager.service.converter.ManagerConverter
import com.ruah.springexample.application.manager.service.port.`in`.ChangePasswordManagerCommand
import com.ruah.springexample.application.manager.service.port.`in`.CreateManagerCommand
import com.ruah.springexample.application.manager.service.port.`in`.EditManagerCommand
import com.ruah.springexample.application.manager.service.port.out.ManagerPort
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ID
import com.ruah.springexample.config.util.makeManagerDto
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ManagerServiceTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val port = mockk<ManagerPort>()
    val converter = mockk<ManagerConverter>(relaxed = true)
    val service = ManagerService(port, converter)

    describe("매니저 회원가입 커맨드") {
        val command = CreateManagerCommand(
            account = "manager",
            name = "매니저",
            email = "email@email.com",
            phone = "010-3456-7890",
            roles = listOf(),
            type = ManagerType.MASTER
        )

        context("정상 케이스") {
            every { port.save(any()) } returns mockk()
            every { port.existsByAccount(any()) } returns false
            every { port.existsByEmail(any()) } returns false
            every { port.existsByPhone(any()) } returns false

            it("port 의 save 를 요청한다.") {
                service.signUp(command)

                verify(exactly = 1) { port.save(any()) }
                verify(exactly = 1) { port.existsByAccount(any()) }
                verify(exactly = 1) { port.existsByEmail(any()) }
                verify(exactly = 1) { port.existsByPhone(any()) }
            }
        }

        context("비정상 케이스") {
            every { port.existsByAccount(any()) } returns false

            it("중복된 계정 오류 발생") {
                every { port.existsByAccount(any()) } returns true
                val exception = shouldThrow<BadRequestException> { service.signUp(command) }
                exception.error shouldBe ErrorCode.DUPLICATE_ACCOUNT
            }
        }
    }

    describe("매니저 정보 수정") {
        val command = EditManagerCommand(
            account = "manager",
            name = "변경된매니저",
            email = "email@email.com",
            phone = "01234567890"
        )

        context("정상 케이스") {
            every { port.get(any()) } returns makeManagerDto()
            every { port.existsByEmail(any()) } returns false
            every { port.existsByPhone(any()) } returns false
            every { port.save(any()) } returns mockk()

            it("정보 수정") {
                service.edit(TEST_MANAGER_ID, command)

                verify(exactly = 1) { port.get(any()) }
                verify(exactly = 1) { port.save(any()) }
                verify(exactly = 1) { port.existsByEmail(any()) }
                verify(exactly = 1) { port.existsByPhone(any()) }
            }
        }

        context("비정상 케이스") {
            every { port.get(any()) } returns makeManagerDto()
            every { port.save(any()) } returns mockk()

            it("중복된 계정 오류 발생") {
                every { port.existsByEmail(any()) } returns true
                val exception = shouldThrow<BadRequestException> { service.edit(TEST_MANAGER_ID, command) }
                exception.error shouldBe ErrorCode.DUPLICATE_EMAIL
            }
        }
    }

    describe("비밀번호 변경") {
        context("정상 케이스") {
            val command = ChangePasswordManagerCommand(
                password = "password123",
                checkPassword = "password123"
            )

            every { port.get(any()) } returns mockk()
            every { port.save(any()) } returns mockk()

            it("비밀번호가 변경됨") {
                service.changePassword(TEST_MANAGER_ID, command)

                verify(exactly = 1) { port.get(any()) }
                verify(exactly = 1) { port.save(any()) }
            }
        }
    }

    describe("매니저 목록 조회") {
        context("정상 케이스") {
            every { port.search(any()) } returns mockk()

            it("매니저 목록 조회") {
                service.search(mockk())
                verify(exactly = 1) { port.search(any()) }
            }
        }
    }

    describe("매니저 회원 탈퇴") {
        context("정상 케이스") {
            every { port.get(any()) } returns makeManagerDto()
            every { port.save(any()) } returns mockk()

            it("회원 탈퇴") {
                service.withdraw(TEST_MANAGER_ID)

                verify(exactly = 1) { port.get(any()) }
                verify(exactly = 1) { port.save(any()) }
            }
        }
    }

    describe("아이디로 매니저 조회") {
        context("정상 케이스") {
            every { port.get(any()) } returns mockk()

            it("해당하는 아이디의 매니저를 조회") {
                service.get(TEST_MANAGER_ID)

                verify(exactly = 1) { port.get(any()) }
            }
        }
    }

    describe("계정 조회") {
        context("정상 케이스") {
            every { port.get(any()) } returns mockk()

            it("계정 조회") {
                service.getAccount(TEST_MANAGER_ID)
                verify(exactly = 1) { port.get(any()) }
            }
        }
    }
})
