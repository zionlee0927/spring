package com.ruah.springexample.application.authentication.service

import com.ruah.springexample.application.authentication.adapter.out.email.SendAuthenticationEmailService
import com.ruah.springexample.application.authentication.adapter.out.phone.SendAuthenticationPhoneService
import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.domain.AuthenticationUserType
import com.ruah.springexample.application.authentication.service.converter.AuthenticationConverter
import com.ruah.springexample.application.authentication.service.port.`in`.IssueAuthenticationNumberCommand
import com.ruah.springexample.application.authentication.service.port.`in`.IssueTemporaryTokenCommand
import com.ruah.springexample.application.authentication.service.port.out.AuthenticationPort
import com.ruah.springexample.application.manager.service.port.`in`.ManagerUseCase
import com.ruah.springexample.config.TestConstant
import com.ruah.springexample.config.TestConstant.Companion.TEST_AUTHENTICATION_NUMBER
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_NAME
import com.ruah.springexample.config.util.makeManagerDto
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify


class AuthenticationServiceTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val port = mockk<AuthenticationPort>()
    val phonePort = spyk<SendAuthenticationPhoneService>()
    val emailPort = spyk<SendAuthenticationEmailService>()
    val sendPortList = listOf(phonePort, emailPort)
    val managerUseCase = mockk<ManagerUseCase>()
    val converter = mockk<AuthenticationConverter>(relaxed = true)
    val jwtUtil = mockk<JwtUtil>(relaxed = true)
    val service = AuthenticationService(port, sendPortList, managerUseCase, converter, jwtUtil)


    describe("인증번호 발급") {
        context("등록된 계정 정보로 요청") {
            val command = IssueAuthenticationNumberCommand(
                userType = AuthenticationUserType.MANAGER,
                name = TEST_MANAGER_NAME,
                platformType = AuthenticationPlatformType.PHONE,
                target = TestConstant.TEST_MANAGER_PHONE
            )

            every { port.save(any()) } returns mockk()
            every { phonePort.send(any())} returns mockk()
            every { managerUseCase.search(any()) } returns listOf(makeManagerDto())

            it("저장하고 요청 정보로 인증번호 발송") {
                service.issueAuthenticationNumber(command)

                verify(exactly = 1) { managerUseCase.search(any()) }
                verify(exactly = 1) { port.save(any()) }
                verify(exactly = 1) { phonePort.send(any()) }
            }
        }
    }

    describe("인증번호 검증") {
        context("계정 정보로 저장된 인증번호 검증") {
            val command = IssueTemporaryTokenCommand(
                userType = AuthenticationUserType.MANAGER,
                name = TEST_MANAGER_NAME,
                platformType = AuthenticationPlatformType.PHONE,
                target = TestConstant.TEST_MANAGER_PHONE,
                number = TEST_AUTHENTICATION_NUMBER,
            )

            every { port.get(any()) } returns mockk()
            every { port.save(any()) } returns mockk()
            every { managerUseCase.search(any()) } returns listOf(makeManagerDto())

            it ("토큰 생성하고 저장") {
                service.issueTemporaryToken(command)

                verify(exactly = 1) { port.get(any()) }
                verify(exactly = 1) { port.save(any()) }
                verify(exactly = 1) { managerUseCase.search(any()) }
            }
        }
    }
})
