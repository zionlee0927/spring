package com.ruah.springexample.framework.util

import com.ruah.springexample.framework.util.DateTimeUtil
import ulid.ULID

class ULIDUtil {

    companion object {
        fun generateULID(order: Int = 0): String {
            return ULID.randomULID(DateTimeUtil.nowToLong() + order)
        }
    }
}