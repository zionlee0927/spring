package com.ruah.springexample.framework.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class RandomUtilTest : FunSpec({

        test("byPercent") {
            val result = RandomUtil.byPercent(50)
            result shouldNotBe null
        }

        test("getRandomNumber") {
            val result = RandomUtil.getRandomNumber(1, 1)
            assertTrue(result in 1..1)
        }

        test("getRandomIndex") {
            val result = RandomUtil.getRandomIndex(100)
            assertTrue(result in 0..99)
        }

        test("getRandomIndexList") {
            val result = RandomUtil.getRandomIndexList(100, 10)
            assertEquals(10, result.size)
        }
})