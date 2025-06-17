package com.ruah.springexample.application.manager.service

import com.ruah.springexample.application.manager.service.converter.ManagerConverter
import com.ruah.springexample.application.manager.service.port.`in`.ManagerLoginCommand
import com.ruah.springexample.application.manager.service.port.out.ManagerPort
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ACCOUNT
import com.ruah.springexample.config.TestConstant.Companion.TEST_PASSWORD
import com.ruah.springexample.config.util.makeManager
import com.ruah.springexample.config.util.makeManagerDto
import com.ruah.springexample.config.util.makeTokenInfo
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ManagerLoginServiceTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val port = mockk<ManagerPort>()
    val converter = mockk<ManagerConverter>()
    val jwtUtil = mockk<JwtUtil>()
    val service = ManagerLoginService(port, converter, jwtUtil)

    describe("매니저 로그인") {
        context("정상") {
            val command = ManagerLoginCommand(
                account = TEST_MANAGER_ACCOUNT,
                password = TEST_PASSWORD
            )

            every { port.search(any()) } returns listOf(makeManagerDto())
            every { converter.toDomain(any())} returns makeManager()
            every { converter.toTokenInfo(any())} returns makeTokenInfo()
            every { jwtUtil.generateManagerToken(any()) } returns Pair("accessToken", "refreshToken")

            it("토큰 정보를 준다.") {
                service.login(command)

                verify(exactly = 1) { port.search(any()) }
            }
        }
    }

    describe("매니저 토큰 재발급") {
        context("정상") {
            every { port.get(any()) } returns mockk()
            every { converter.toDomain(any())} returns makeManager()
            every { converter.toTokenInfo(any())} returns makeTokenInfo()
            every { jwtUtil.generateManagerToken(any()) } returns Pair("accessToken", "refreshToken")

            it("엑세스 토큰을 준다") {
                service.refresh(TEST_MANAGER_ACCOUNT)

                verify(exactly = 1) { port.get(any()) }
            }
        }
    }
})