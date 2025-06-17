package com.ruah.springexample.application.member.adapter.`in`.web

import com.ruah.springexample.application.member.service.port.`in`.MemberLoginUseCase
import com.ruah.springexample.application.member.adapter.`in`.converter.MemberCommandConverter
import com.ruah.springexample.application.member.adapter.`in`.web.dto.MemberLoginRequest
import com.ruah.springexample.framework.config.security.dto.MemberDetails
import com.ruah.springexample.framework.config.security.dto.TokenResponse
import com.ruah.springexample.framework.wrapper.SingleValueWrapper
import com.ruah.springexample.logger
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberLoginController(
    private val useCase: MemberLoginUseCase,
    private val converter: MemberCommandConverter
) {
    val log = logger()

    @PostMapping("/through/login")
    fun login(@RequestBody request: MemberLoginRequest): TokenResponse {
        log.info("login request: $request")
        val command = converter.toCommand(request)
        return useCase.login(command)
    }

    @PostMapping("/api/refresh")
    fun refresh(@AuthenticationPrincipal memberDetail: MemberDetails) : SingleValueWrapper<String> {
        val token = useCase.refresh(memberDetail.id)
        return SingleValueWrapper(token)
    }
}