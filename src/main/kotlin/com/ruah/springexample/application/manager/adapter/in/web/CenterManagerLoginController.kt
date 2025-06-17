package com.ruah.springexample.application.manager.adapter.`in`.web

import com.ruah.springexample.application.manager.service.port.`in`.ManagerLoginUseCase
import com.ruah.springexample.application.manager.adapter.`in`.converter.ManagerCommandConverter
import com.ruah.springexample.application.manager.adapter.`in`.web.dto.ManagerLoginRequest
import com.ruah.springexample.framework.config.security.dto.ManagerDetails
import com.ruah.springexample.framework.config.security.dto.TokenResponse
import com.ruah.springexample.framework.wrapper.SingleValueWrapper
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class CenterManagerLoginController(
    private val useCase: ManagerLoginUseCase,
    private val converter: ManagerCommandConverter
) {

    @PostMapping("/through/center/login")
    fun login(@RequestBody request: ManagerLoginRequest) : TokenResponse {
        val command = converter.toCommand(request)
        return useCase.login(command)
    }

    @PostMapping("/center/refresh")
    fun refresh(@AuthenticationPrincipal managerDetails: ManagerDetails) : SingleValueWrapper<String> {
        val token = useCase.refresh(managerDetails.id)
        return SingleValueWrapper(token)
    }
}