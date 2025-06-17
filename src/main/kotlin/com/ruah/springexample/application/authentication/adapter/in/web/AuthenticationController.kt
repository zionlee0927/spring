package com.ruah.springexample.application.authentication.adapter.`in`.web

import com.ruah.springexample.application.authentication.adapter.converter.AuthenticationCommandConverter
import com.ruah.springexample.application.authentication.adapter.`in`.web.dto.IssueAuthenticationNumberRequest
import com.ruah.springexample.application.authentication.adapter.`in`.web.dto.IssueTemporaryTokenRequest
import com.ruah.springexample.application.authentication.service.port.`in`.AuthenticationUseCase
import com.ruah.springexample.framework.wrapper.SingleValueWrapper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class AuthenticationController(
    private val useCase: AuthenticationUseCase,
    private val converter: AuthenticationCommandConverter
) {

    @PostMapping("/through/v1/authentication/number")
    fun issueAuthenticationNumber(@RequestBody request: IssueAuthenticationNumberRequest): SingleValueWrapper<String> {
        val command = converter.toCommand(request)
        val number = useCase.issueAuthenticationNumber(command)

        // TODO 이메일 / 문자 발송 개발 완료 후, 인증번호 리턴 안 하도록 변경.


        return SingleValueWrapper(number)
    }

    @PostMapping("/through/v1/authentication/token")
    fun issueTemporaryToken(@RequestBody request: IssueTemporaryTokenRequest): SingleValueWrapper<String> {
        val command = converter.toCommand(request)
        val temporaryToken = useCase.issueTemporaryToken(command)

        return SingleValueWrapper(temporaryToken)
    }
}