package com.ruah.springexample.application.manager.adapter.`in`.web

import com.ruah.springexample.application.manager.adapter.`in`.web.dto.CreateManagerRequest
import com.ruah.springexample.application.manager.adapter.`in`.web.dto.EditManagerRequest
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.config.restdoc.RestDocsIntegrationTestSupport
import com.ruah.springexample.config.restdoc.supporter.model.DATETIME
import com.ruah.springexample.config.restdoc.supporter.model.ENUM
import com.ruah.springexample.config.restdoc.supporter.model.STRING
import com.ruah.springexample.config.restdoc.supporter.type
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CenterManagerControllerTest : RestDocsIntegrationTestSupport() {

    init {
        describe("매니저 등록") {
            context("매니저 등록 요청") {
                val request = CreateManagerRequest(
                    account = "newAccount",
                    name = "newName",
                    email = "newEmail@beaubrain.bio",
                    phone = "010-9898-2828"
                )
                it("매니저가 등록되고 정보를 받는다") {
                    val result: ResultActions = mockMvc.perform(
                        post("/center/manager")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.AUTHORIZATION, testDataSupport.testMasterToken)
                            .content(objectMapper.writeValueAsString(request))
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "register_manager",
                            requestBody(
                                "account" type STRING means "로그인 계정",
                                "name" type STRING means "이름",
                                "email" type STRING means "이메일",
                                "phone" type STRING means "연락처"
                            ),
                            responseBody(
                                "id" type STRING means "아이디",
                                "account" type STRING means "로그인 계정",
                                "name" type STRING means "이름",
                                "email" type STRING means "이메일",
                                "phone" type STRING means "연락처",
                                "type" type ENUM(ManagerType::class) means "매니저 타입",
                                "createdAt" type DATETIME means "생성 일시"
                            )
                        )
                    )
                }
            }
        }

        describe("매니저 정보 수정") {
            context("매니저 정보 수정") {
                val request = EditManagerRequest(
                    account = "newAccount",
                    name = "매니저",
                    email = "email@email.net",
                    phone = "012-3456-7890"
                )

                it("매니저 정보가 수정된다.") {
                    val result: ResultActions = mockMvc.perform(
                        put("/center/manager/{managerId}", testDataSupport.testManagerDto.id)
                            .header(HttpHeaders.AUTHORIZATION, testDataSupport.testMasterToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "edit_manager",
                            requestBody(
                                "account" type STRING means "로그인 계정",
                                "name" type STRING means "이름",
                                "email" type STRING means "이메일",
                                "phone" type STRING means "연락처"
                            ),
                            responseBody(
                                "id" type STRING means "아이디",
                                "account" type STRING means "로그인 계정",
                                "name" type STRING means "이름",
                                "email" type STRING means "이메일",
                                "phone" type STRING means "연락처",
                                "type" type ENUM(ManagerType::class) means "매니저 타입",
                                "createdAt" type DATETIME means "생성 일시"
                            )
                        )
                    )
                }
            }
        }

        describe("매니저 회원 삭제 API") {
            context("매니저 회원 삭제 요청") {
                it("매니저가 회원 탈퇴가 된다.") {
                    val result: ResultActions = mockMvc.perform(
                        delete("/center/manager/{managerId}", testDataSupport.testMasterDto.id)
                            .header(HttpHeaders.AUTHORIZATION, testDataSupport.testMasterToken)
                            .contentType(MediaType.APPLICATION_JSON)
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "withdraw_manager"
                        )
                    )
                }
            }
        }

        describe("매니저 상세조회") {
            context("매니저 ID 로 정보 조회") {
                it("매니저 정보를 받는다") {
                    val result: ResultActions = mockMvc.perform(
                        get("/center/manager/{managerId}", testDataSupport.testManagerDto.id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .header(HttpHeaders.AUTHORIZATION, testDataSupport.testMasterToken)
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "get_manager",
                            pathVariable(
                                "managerId" type STRING means "매니저 아이디",
                            ),
                            responseBody(
                                "id" type STRING means "아이디",
                                "account" type STRING means "로그인 계정",
                                "name" type STRING means "이름",
                                "email" type STRING means "이메일",
                                "phone" type STRING means "연락처",
                                "type" type ENUM(ManagerType::class) means "매니저 타입",
                                "createdAt" type DATETIME means "생성 일시"
                            )
                        )
                    )
                }
            }
        }

        describe("매니저 본인 정보 수정") {
            context("매니저 본인의 정보 수정") {
                val request = EditManagerRequest(
                    account = "newAccount",
                    name = "매니저",
                    email = "email@email.net",
                    phone = "012-3456-7890"
                )

                it("매니저 정보가 수정된다.") {
                    val result: ResultActions = mockMvc.perform(
                        put("/center/manager")
                            .header(HttpHeaders.AUTHORIZATION, testDataSupport.testManagerToken)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    ).andExpect(status().isOk)
                        .andDo(print())

                    result.andDo(
                        document(
                            "edit_manager_self",
                            requestBody(
                                "account" type STRING means "로그인 계정",
                                "name" type STRING means "이름",
                                "email" type STRING means "이메일",
                                "phone" type STRING means "연락처"
                            ),
                            responseBody(
                                "id" type STRING means "아이디",
                                "account" type STRING means "로그인 계정",
                                "name" type STRING means "이름",
                                "email" type STRING means "이메일",
                                "phone" type STRING means "연락처",
                                "type" type ENUM(ManagerType::class) means "매니저 타입",
                                "createdAt" type DATETIME means "생성 일시"
                            )
                        )
                    )
                }
            }
        }
    }
}