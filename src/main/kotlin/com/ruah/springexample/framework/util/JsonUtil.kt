package com.ruah.springexample.framework.util

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import org.springframework.util.LinkedMultiValueMap
import java.io.IOException

class JsonUtil {
    companion object {
        val logger = LoggerFactory.getLogger(JsonUtil::class.java)
        var mapper: ObjectMapper
        var notUseMapperJsonProperty: ObjectMapper

        init {
            val f: JsonFactory =
                JsonFactory.builder()
                    .disable(JsonFactory.Feature.INTERN_FIELD_NAMES)
                    .build()

            notUseMapperJsonProperty = JsonMapper.builder(f)
                .build()

            mapper = JsonMapper.builder(f)
                .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)
                .build()

            mapper.registerKotlinModule()
            mapper.registerModule(JavaTimeModule())
            mapper.registerModule(Jdk8Module())
        }

        fun generateClassToJson(obj: Any?): String? {
            if (obj == null) {
                return null
            }
            var json: String? = null
            try {
                json = mapper.writeValueAsString(obj)
            } catch (e: IOException) {
                logger.error(String.format("Parse class to string - errorMsg:%s", e.message))
            }
            return json
        }

        fun <Any> generateJsonToClass(jsonData: String?, valueTypeRef: TypeReference<Any>): Any? {
            if (jsonData == null) {
                return null
            }
            var `object`: Any? = null
            try {
                `object` = mapper.readValue<Any>(jsonData, valueTypeRef)
            } catch (e: IOException) {
                logger.error(String.format("Parse string to class - errorMsg:%s", e.message))
            }
            return `object`
        }

        fun <T> generateJsonToClass(jsonData: String?, valueTypeRef: Class<T>?): T? {
            if (jsonData == null) {
                return null
            }
            var `object`: T? = null
            try {
                `object` = mapper.readValue(jsonData, valueTypeRef)
            } catch (e: IOException) {
                logger.error(String.format("Parse string to class - errorMsg:%s", e.message))
            }
            return `object`
        }

        fun <T> generateObjectToMap(o: Any?, valueTypeRef: Class<T>): T? {
            return if (o == null) {
                null
            } else mapper.convertValue(o, valueTypeRef)
        }

        fun generateDtoToMultiValueMap(dto: Any?): LinkedMultiValueMap<String, String> =
            if (dto == null) {
                LinkedMultiValueMap<String, String>()
            } else {
                mapper
                    .convertValue<Map<String, String>>(dto)
                    .let { LinkedMultiValueMap<String, String>().apply { setAll(it) } }
            }
    }
}
