package com.ruah.springexample.application.authentication.adapter.out.email

import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.service.port.out.SendAuthenticationPort
import org.springframework.stereotype.Service

@Service
class SendAuthenticationEmailService(

) : SendAuthenticationPort {
    override val type: AuthenticationPlatformType = AuthenticationPlatformType.EMAIL
    override fun send(target: String) {
        // TODO Email 발송 개발 필요
        return
    }
}