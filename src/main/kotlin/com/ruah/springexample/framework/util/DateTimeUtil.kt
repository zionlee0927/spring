package com.ruah.springexample.framework.util

import java.time.LocalDateTime
import java.time.ZoneId

class DateTimeUtil {

    companion object {
        const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        const val DATE_FORMAT = "yyyy-MM-dd"
        const val TIME_FORMAT = "HH:mm:ss"

        fun nowToLong(): Long {
            return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }
    }
}