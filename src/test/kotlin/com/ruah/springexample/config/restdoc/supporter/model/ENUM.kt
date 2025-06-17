package com.ruah.springexample.config.restdoc.supporter.model

import com.ruah.springexample.global.CodeValue
import org.springframework.restdocs.payload.JsonFieldType
import kotlin.reflect.KClass

data class ENUM<T : CodeValue>(val name: String?, val enums: Collection<T>) : DocsFieldType(JsonFieldType.STRING) {
    constructor(clazz: KClass<T>) : this(clazz.simpleName, clazz.java.enumConstants.asList())
}
