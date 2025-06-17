package com.ruah.springexample.framework.util

class KoreanUtil {
    companion object {
        fun extractInitials(input: String): String {
            val initialConsonants = listOf(
                'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
            )
            val result = StringBuilder()

            for (char in input) {
                if (char in '가'..'힣') {
                    val unicodeValue = char.code - 0xAC00
                    val initialIndex = unicodeValue / (21 * 28)
                    result.append(initialConsonants[initialIndex])
                }
            }

            return result.toString()
        }
    }
}
