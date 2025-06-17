package com.ruah.springexample.application.manager.adapter.`in`.web

import com.ruah.springexample.application.manager.service.port.`in`.ManagerUseCase
import com.ruah.springexample.application.manager.adapter.`in`.converter.ManagerCommandConverter
import com.ruah.springexample.application.manager.adapter.`in`.web.dto.ChangePasswordManagerRequest
import com.ruah.springexample.application.manager.adapter.`in`.web.dto.CreateManagerRequest
import com.ruah.springexample.application.manager.adapter.`in`.web.dto.EditManagerRequest
import com.ruah.springexample.application.manager.adapter.`in`.web.dto.ManagerResponse
import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.framework.config.security.dto.ManagerDetails
import com.ruah.springexample.framework.config.security.role.annotation.ManagerRoleRequired
import com.ruah.springexample.framework.wrapper.SingleValueWrapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/center/manager")
class CenterManagerController(
    private val useCase: ManagerUseCase,
    private val converter: ManagerCommandConverter
) {

    @PostMapping
    fun signUp(@RequestBody request: CreateManagerRequest): ManagerResponse {
        val command = converter.toCommand(request, ManagerType.MANAGER)
        val result = useCase.signUp(command)
        return converter.toResponse(result)
    }

    @GetMapping("/{managerId}")
    fun get(@PathVariable managerId: String): ManagerResponse {
        val result = useCase.get(managerId)
        return converter.toResponse(result)
    }

    @GetMapping("/me")
    fun getMe(
        @AuthenticationPrincipal managerDetails: ManagerDetails
    ): ManagerResponse {
        val result = useCase.get(managerDetails.id)
        return converter.toResponse(result)
    }

    @PutMapping("/{managerId}")
    @ManagerRoleRequired(role = ManagerRole.ROLE_MANAGER_MANAGE)
    fun edit(
        @PathVariable managerId: String,
        @RequestBody request: EditManagerRequest,
    ): ManagerResponse {
        val command = converter.toCommand(request)
        val result = useCase.edit(managerId, command)
        return converter.toResponse(result)
    }

    @PutMapping
    fun editMe(
        @RequestBody request: EditManagerRequest,
        @AuthenticationPrincipal managerDetails: ManagerDetails
    ): ManagerResponse {
        val command = converter.toCommand(request)
        val result = useCase.edit(managerDetails.id, command)
        return converter.toResponse(result)
    }

    @PatchMapping("/password")
    fun changePassword(
        @RequestBody request: ChangePasswordManagerRequest,
        @AuthenticationPrincipal managerDetails: ManagerDetails
    ): ResponseEntity<Any> {
        val command = converter.toCommand(request)
        useCase.changePassword(managerDetails.id, command)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/account")
    fun getAccount(
        @AuthenticationPrincipal managerDetails: ManagerDetails
    ): SingleValueWrapper<String> {
        val account = useCase.getAccount(managerDetails.id)
        return SingleValueWrapper(account)
    }

    @DeleteMapping("/{managerId}")
    @ManagerRoleRequired(role = ManagerRole.ROLE_MANAGER_MANAGE)
    fun withdraw(
        @PathVariable managerId: String
    ): ResponseEntity<Any> {
        useCase.withdraw(managerId)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping
    fun withdrawMe(
        @AuthenticationPrincipal managerDetails: ManagerDetails
    ): ResponseEntity<Any> {
        useCase.withdraw(managerDetails.id)
        return ResponseEntity(HttpStatus.OK)
    }
}