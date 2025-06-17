package com.ruah.springexample.application.authentication.adapter.out.phone

import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.service.port.out.SendAuthenticationPort
import org.springframework.stereotype.Service

@Service
class SendAuthenticationPhoneService(

) : SendAuthenticationPort {
    override val type: AuthenticationPlatformType = AuthenticationPlatformType.PHONE
    override fun send(target: String) {
        // TODO 문자 서비스 계약하고 개발 필요
        return
    }
}