package com.ruah.springexample.config.restdoc.supporter

import com.ruah.springexample.config.restdoc.supporter.model.ENUM
import com.ruah.springexample.global.CodeValue

class EnumFormattingUtils {

    companion object {
        fun <T : CodeValue> enumFormat(enum: ENUM<T>): String {
            val enums = enum.enums.joinToString(
                prefix = "[ ",
                postfix = " ]",
                separator = ", "
            ) {
                "${it.getName()}(${it.getDescription()})"
            }

            return "${enum.name} \n $enums"
        }
    }
}