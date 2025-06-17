package com.ruah.springexample.application.manager.adapter.`in`.web

import com.ruah.springexample.application.manager.adapter.`in`.web.dto.ManagerLoginRequest
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ACCOUNT
import com.ruah.springexample.config.TestConstant.Companion.TEST_PASSWORD
import com.ruah.springexample.config.restdoc.RestDocsIntegrationTestSupport
import com.ruah.springexample.config.restdoc.supporter.model.STRING
import com.ruah.springexample.config.restdoc.supporter.type
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class CenterManagerLoginControllerTest : RestDocsIntegrationTestSupport() {

    init {
        describe("매니저 로그인") {
            context("계정과 비밀번호를 입력받고") {
                val request = ManagerLoginRequest(
                    account = TEST_MANAGER_ACCOUNT,
                    password = TEST_PASSWORD
                )

                it("토큰 정보를 준다.") {
                    val result: ResultActions = mockMvc.perform(
                        post("/through/center/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "manager_login",
                            requestBody(
                                "account" type STRING means "로그인 계정",
                                "password" type STRING means "로그인 패스워드",
                            ),
                            responseBody(
                                "accessToken" type STRING means "Access Token",
                                "refreshToken" type STRING means "Refresh Token",
                            )
                        )
                    )
                }
            }
        }

        describe("매니저 토큰 재발급") {
            context("Refresh Token 으로 재발급") {
                it("액세스 토큰을 준다.") {
                    val result: ResultActions = mockMvc.perform(
                        post("/center/refresh")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.AUTHORIZATION, testDataSupport.testManagerToken)
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "manager_refresh_token",
                            responseBody(
                                "value" type STRING means "Access Token"
                            )
                        )
                    )
                }
            }
        }
    }
}