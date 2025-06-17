package com.ruah.springexample.config.restdoc.supporter.model

import org.springframework.restdocs.payload.JsonFieldType

sealed class DocsFieldType(
    val type: JsonFieldType,
)
object ANY : DocsFieldType(JsonFieldType.VARIES)
object ARRAY : DocsFieldType(JsonFieldType.ARRAY)
object OBJECT : DocsFieldType(JsonFieldType.OBJECT)
object BOOLEAN : DocsFieldType(JsonFieldType.BOOLEAN)
object NUMBER : DocsFieldType(JsonFieldType.NUMBER)
object STRING : DocsFieldType(JsonFieldType.STRING)
object TIME : DocsFieldType(JsonFieldType.STRING)
object DATE : DocsFieldType(JsonFieldType.STRING)
object DATETIME : DocsFieldType(JsonFieldType.STRING)
object NULL : DocsFieldType(JsonFieldType.NULL)