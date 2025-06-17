package com.ruah.springexample.framework.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MathUtilTest : FunSpec({

    test("gcd") {
        val gcd = MathUtil.gcd(2, 3)

        gcd shouldBe 1
    }
})