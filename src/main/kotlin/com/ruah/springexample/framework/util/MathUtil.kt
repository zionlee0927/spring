package com.ruah.springexample.framework.util

class MathUtil {

    companion object {
        // Greatest Common Divisor
        fun gcd(a: Int, b: Int): Int {
            return if (b == 0) a else gcd(b, a % b)
        }

        // Least Common Multiple
        fun lcm(a: Int, b: Int): Int {
            return a * b / gcd(a, b)
        }
    }
}