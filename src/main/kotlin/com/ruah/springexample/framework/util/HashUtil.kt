package com.ruah.springexample.framework.util

class HashUtil {

    companion object {
        private val numberSet = ('0'..'9')
        private val alphabetSet = ('a'..'z') + ('A'..'Z')
        private val charSet = numberSet + alphabetSet

        fun randomNumber(length: Int): String {
            return List(length) { numberSet.random() }.joinToString("")
        }

        fun randomAlphabet(length: Int): String {
            return List(length) { alphabetSet.random() }.joinToString("")
        }

        fun randomChar(length: Int): String {
            return List(length) { charSet.random() }.joinToString("")
        }
    }
}