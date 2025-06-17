package com.ruah.springexample.application.authentication.service.port.`in`

interface AuthenticationUseCase {

    fun issueAuthenticationNumber(command: IssueAuthenticationNumberCommand): String
    fun issueTemporaryToken(command: IssueTemporaryTokenCommand): String
}