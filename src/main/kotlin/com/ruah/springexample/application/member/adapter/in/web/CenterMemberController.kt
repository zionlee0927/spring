package com.ruah.springexample.application.member.adapter.`in`.web

import com.ruah.springexample.application.member.adapter.`in`.converter.MemberCommandConverter
import com.ruah.springexample.application.member.adapter.`in`.web.dto.ChangePasswordMemberRequest
import com.ruah.springexample.application.member.adapter.`in`.web.dto.EditMemberRequest
import com.ruah.springexample.application.member.adapter.`in`.web.dto.MemberResponse
import com.ruah.springexample.application.member.adapter.`in`.web.dto.RegisterMemberRequest
import com.ruah.springexample.application.member.service.port.`in`.MemberUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/center")
class CenterMemberController(
    private val useCase: MemberUseCase,
    private val converter: MemberCommandConverter
) {

    @PostMapping("/member")
    fun register(@RequestBody request: RegisterMemberRequest): MemberResponse {
        val command = converter.toCommand(request)
        val dto = useCase.register(command)
        return converter.toResponse(dto)
    }

    @PutMapping("/member/{memberId}")
    fun edit(
        @PathVariable memberId: String,
        @RequestBody request: EditMemberRequest
    ): MemberResponse {
        val command = converter.toCommand(request)
        val member = useCase.edit(memberId, command)
        return converter.toResponse(member)
    }

    @PatchMapping("/member/{memberId}/password")
    fun changePassword(
        @RequestBody request: ChangePasswordMemberRequest,
        @PathVariable memberId: String,
    ): ResponseEntity<Any> {
        val command = converter.toCommand(request)
        useCase.changePassword(memberId, command)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("/member/{memberId}")
    fun delete(
        @PathVariable memberId: String,
    ): ResponseEntity<Any> {
        useCase.delete(memberId)
        return ResponseEntity(HttpStatus.OK)
    }
}