package com.ruah.springexample.framework.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EncryptUtilTest : FunSpec({

    test("Argon2 알고리즘으로 암호화하고 검증한다.") {
        val source = "password1"
        val encrypted = EncryptUtil.Companion.encryptByArgon2(source)
        val result = EncryptUtil.Companion.matchByArgon2(source, encrypted)

        result shouldBe true
    }
})