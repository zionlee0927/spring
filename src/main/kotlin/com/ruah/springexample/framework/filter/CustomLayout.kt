package com.ruah.springexample.framework.filter

import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.config.Node
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginAttribute
import org.apache.logging.log4j.core.config.plugins.PluginFactory
import org.apache.logging.log4j.core.layout.AbstractStringLayout
import org.apache.logging.log4j.message.SimpleMessage
import org.json.JSONObject
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Plugin(name = "CustomLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
class CustomLayout(charset: Charset?) : AbstractStringLayout(charset) {
    override fun toSerializable(event: LogEvent): String {
        if (event.message is SimpleMessage) {
            val createdLog = JSONObject(object : HashMap<String?, Any?>() {
                init {
                    put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                    put("message", event.message.formattedMessage)
                }
            })
            return createdLog.toString() + DEFAULT_EOL
        } else {
            return event.message.formattedMessage + DEFAULT_EOL
        }
    }

    companion object {
        private const val DEFAULT_EOL = "\r\n"

        @JvmStatic
        @PluginFactory
        fun customLayout(@PluginAttribute(value = "charset", defaultString = "UTF-8") charset: Charset?): CustomLayout {
            return CustomLayout(charset)
        }
    }
}