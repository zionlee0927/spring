package com.ruah.springexample.framework.util

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8

class EncryptUtil {

    companion object {
        private val argon2Encoder = defaultsForSpringSecurity_v5_8()

        fun encryptByArgon2(source: String): String {
            return argon2Encoder.encode(source)
        }

        fun matchByArgon2(source: String, encrypted: String): Boolean {
            return argon2Encoder.matches(source, encrypted)
        }
    }
}