package com.ruah.springexample.framework.util

class RandomUtil {

    companion object {

        fun byPercent(percentage: Int): Boolean {
            return getRandomNumber(100) <= percentage
        }

        fun getRandomNumber(max: Int): Int {
            return getRandomNumber(1, max)
        }

        fun getRandomNumber(min: Int, max: Int): Int {
            return (min..max).shuffled().first()
        }

        fun getRandomIndex(maxIndex: Int): Int {
            return getRandomIndexList(maxIndex, 1).first()
        }

        fun getRandomIndexList(maxIndex: Int, count: Int): List<Int> {
            return (0..<maxIndex).shuffled().take(count)
        }
    }
}