package com.ruah.springexample.application.member.adapter.`in`.web

import com.ruah.springexample.application.member.service.port.`in`.MemberUseCase
import com.ruah.springexample.application.member.adapter.`in`.converter.MemberCommandConverter
import com.ruah.springexample.application.member.adapter.`in`.web.dto.MemberResponse
import com.ruah.springexample.framework.config.security.dto.MemberDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val useCase: MemberUseCase,
    private val converter: MemberCommandConverter
) {
    @GetMapping("/me")
    fun getMe(
        @AuthenticationPrincipal memberDetails: MemberDetails
    ) : MemberResponse {
        val dto = useCase.get(memberDetails.id)
        return converter.toResponse(dto)
    }
}