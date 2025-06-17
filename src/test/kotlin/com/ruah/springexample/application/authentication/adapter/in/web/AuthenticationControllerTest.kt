package com.ruah.springexample.application.authentication.adapter.`in`.web

import com.ruah.springexample.application.authentication.adapter.`in`.web.dto.IssueAuthenticationNumberRequest
import com.ruah.springexample.application.authentication.adapter.`in`.web.dto.IssueTemporaryTokenRequest
import com.ruah.springexample.application.authentication.adapter.out.persistence.dao.AuthenticationDao
import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.domain.AuthenticationUserType
import com.ruah.springexample.application.authentication.service.port.dto.AuthenticationDto
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_NAME
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_PHONE
import com.ruah.springexample.config.restdoc.RestDocsIntegrationTestSupport
import com.ruah.springexample.config.restdoc.supporter.model.ENUM
import com.ruah.springexample.config.restdoc.supporter.model.STRING
import com.ruah.springexample.config.restdoc.supporter.type
import makeAuthenticationDto
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class AuthenticationControllerTest(
    private val authenticationDao: AuthenticationDao
) : RestDocsIntegrationTestSupport() {

    init {
        describe("인증번호를 발송한다.") {
            context("등록된 전화번호 또는 이메일로 인증번호를 발송한다.") {
                val request = IssueAuthenticationNumberRequest(
                    userType = AuthenticationUserType.MANAGER,
                    name = TEST_MANAGER_NAME,
                    platformType = AuthenticationPlatformType.PHONE,
                    target = TEST_MANAGER_PHONE
                )

                it("이름과 전화번호 또는 이메일로 검색해서 인증번호를 발송한다.") {
                    val result: ResultActions = mockMvc.perform(
                        post("/through/v1/authentication/number")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "issue_authentication_number",
                            requestBody(
                                "userType" type ENUM(AuthenticationUserType::class) means "유저 타입",
                                "name" type STRING means "이름",
                                "platformType" type ENUM(AuthenticationPlatformType::class) means "발송 플랫폼",
                                "target" type STRING means "발송 타겟(전화번호, 이메일)"
                            ),
                            responseBody(
                                "value" type STRING means "인증번호 - 임시로 Response에 포함. 향후 삭제."
                            )
                        )
                    )
                }
            }
        }

        describe("인증번호를 검증한다.") {
            val authentication = saveAuthentication()
            context("입력된 비밀번호와 저장된 인증번호를 비교한다.") {
                val request = IssueTemporaryTokenRequest(
                    userType = AuthenticationUserType.MANAGER,
                    name = TEST_MANAGER_NAME,
                    platformType = authentication.platformType,
                    target = authentication.target,
                    number = authentication.number
                )

                it("인증번호가 일치한다면 임시 토큰을 발급한다.") {
                    val result: ResultActions = mockMvc.perform(
                        post("/through/v1/authentication/token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "issue_authentication_temporary_token",
                            requestBody(
                                "userType" type ENUM(AuthenticationUserType::class) means "유저 타입",
                                "name" type STRING means "이름",
                                "platformType" type ENUM(AuthenticationPlatformType::class) means "발송 플랫폼",
                                "target" type STRING means "발송 타겟(전화번호, 이메일)",
                                "number" type STRING means "인증번호"
                            ),
                            responseBody(
                                "value" type STRING means "임시 토큰"
                            )
                        )
                    )
                }
            }
        }
    }

    private fun saveAuthentication() : AuthenticationDto {
        return authenticationDao.save(makeAuthenticationDto())
    }
}