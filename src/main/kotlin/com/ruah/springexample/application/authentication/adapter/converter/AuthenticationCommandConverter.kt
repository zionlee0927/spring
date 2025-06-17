package com.ruah.springexample.application.authentication.adapter.converter

import com.ruah.springexample.application.authentication.adapter.`in`.web.dto.IssueAuthenticationNumberRequest
import com.ruah.springexample.application.authentication.adapter.`in`.web.dto.IssueTemporaryTokenRequest
import com.ruah.springexample.application.authentication.service.port.`in`.IssueAuthenticationNumberCommand
import com.ruah.springexample.application.authentication.service.port.`in`.IssueTemporaryTokenCommand
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AuthenticationCommandConverter {

    fun toCommand(request: IssueAuthenticationNumberRequest): IssueAuthenticationNumberCommand
    fun toCommand(request: IssueTemporaryTokenRequest): IssueTemporaryTokenCommand
}