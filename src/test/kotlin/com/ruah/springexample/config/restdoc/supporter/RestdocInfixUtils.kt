package com.ruah.springexample.config.restdoc.supporter

import com.ruah.springexample.config.restdoc.supporter.model.*
import com.ruah.springexample.framework.util.DateTimeUtil.Companion.DATE_FORMAT
import com.ruah.springexample.framework.util.DateTimeUtil.Companion.DATE_TIME_FORMAT
import com.ruah.springexample.framework.util.DateTimeUtil.Companion.TIME_FORMAT
import com.ruah.springexample.global.CodeValue
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation

infix fun String.type(docsFieldType: DocsFieldType): Field {
    val field = createField(this, docsFieldType.type)
    when (docsFieldType) {
        is TIME -> field formattedAs TIME_FORMAT
        is DATE -> field formattedAs DATE_FORMAT
        is DATETIME -> field formattedAs DATE_TIME_FORMAT
        else -> {}
    }
    return field
}

infix fun <T : CodeValue> String.type(enumFieldType: ENUM<T>): Field {
    val field = createField(this, JsonFieldType.STRING)
    field.fieldFormat = EnumFormattingUtils.enumFormat(enumFieldType)
    field.parameterFormat = EnumFormattingUtils.enumFormat(enumFieldType)
    return field
}

private fun createField(value: String, type: JsonFieldType): Field {
    val fieldDescriptor = PayloadDocumentation.fieldWithPath(value)
        .type(type)
        .attributes(RestDocsUtils.emptySample(), RestDocsUtils.emptyFormat(), RestDocsUtils.emptyDefaultValue())
        .description("")

    val parameterDescriptor = RequestDocumentation.parameterWithName(value)
        .description("")

    return Field(fieldDescriptor, parameterDescriptor)
}
