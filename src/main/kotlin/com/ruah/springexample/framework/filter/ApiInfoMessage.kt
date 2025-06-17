package com.ruah.springexample.framework.filter

import org.apache.logging.log4j.message.Message
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ApiInfoMessage(
    private val apiInfo: HttpLogMessage
) : Message {

    override fun getFormattedMessage(): String {
        val realLog = JSONObject(apiInfo)
        val createdLog = JSONObject(object : HashMap<String?, Any?>() {
            init {
                put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                put("apiInfo", realLog)
            }
        })
        return createdLog.toString()
    }

    override fun getFormat(): String {
        return apiInfo.toString()
    }

    override fun getParameters(): Array<Any?> {
        return arrayOfNulls(0)
    }

    override fun getThrowable(): Throwable? {
        return null
    }
}