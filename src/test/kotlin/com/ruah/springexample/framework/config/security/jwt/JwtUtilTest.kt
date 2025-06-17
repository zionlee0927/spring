package com.ruah.springexample.framework.config.security.jwt

import com.ruah.springexample.config.util.makeTokenInfo
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JwtUtilTest : FunSpec({
    val util = JwtUtil(
        apiKey = "apiSecretKeyApiSecretKeyApiSecretKeyApiSecretKeyApiSecretKeyApiSecretKey",
        centerKey = "centerSecretKeyCenterSecretKeyCenterSecretKeyCenterSecretKeyCenterSecretKey",
        adminKey = "adminSecretKeyAdminSecretKeyAdminSecretKeyAdminSecretKeyAdminSecretKeyAdminSecretKey"
    )

    val tokenInfo = makeTokenInfo()

    test("Generate Token") {
        val token = util.generateManagerToken(tokenInfo, 20 * 365 * 24 * 60 * 60 * 1000L)
        println("token: >$token<")
        token shouldNotBe null
    }

    test("Verify Token") {
        val token = util.generateManagerToken(tokenInfo, 20 * 365 * 24 * 60 * 60 * 1000L)
        val managerDetail = util.getManagerInfo(token)

        managerDetail.id shouldBe tokenInfo.id
        managerDetail.name shouldBe tokenInfo.name
        managerDetail.role shouldBe tokenInfo.roles.map { SimpleGrantedAuthority(it) }
    }
})
