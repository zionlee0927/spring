package com.ruah.springexample.framework.util

import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Assertions.assertTrue

class ULIDUtilTest: FunSpec({
    test("generateULID") {
        val ulid = ULIDUtil.generateULID()
        assertTrue(ulid.length == 26)
    }
})