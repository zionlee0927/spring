package com.ruah.springexample.config.restdoc.supporter

import com.ruah.springexample.config.restdoc.supporter.model.Field
import io.kotest.core.spec.style.DescribeSpec
import org.junit.jupiter.api.Disabled
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.QueryParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation

@Disabled
open class SnippetSupporter : DescribeSpec() {

    fun pathVariable(vararg fields: Field): PathParametersSnippet {
        var pathVariables: MutableList<ParameterDescriptor> = mutableListOf()
        fields.forEach {
            pathVariables.add(it.parameterDescriptor)
        }
        return RequestDocumentation.pathParameters(pathVariables)
    }

    fun queryParameter(vararg fields: Field): QueryParametersSnippet {
        var queryParameters: MutableList<ParameterDescriptor> = mutableListOf()
        fields.forEach {
            queryParameters.add(it.parameterDescriptor)
        }
        return RequestDocumentation.queryParameters(queryParameters)
    }

    fun requestBody(vararg fields: Field): RequestFieldsSnippet {
        var requestBody: MutableList<FieldDescriptor> = mutableListOf()
        fields.forEach {
            requestBody.add(it.fieldDescriptor)
        }
        return PayloadDocumentation.requestFields(requestBody)
    }

    fun responseBody(vararg fields: Field): ResponseFieldsSnippet {
        var responseBody: MutableList<FieldDescriptor> = mutableListOf()
        fields.forEach {
            responseBody.add(it.fieldDescriptor)
        }
        return PayloadDocumentation.responseFields(responseBody)
    }

    fun relaxedRequestBody(vararg fields: Field): RequestFieldsSnippet {
        var requestBody: MutableList<FieldDescriptor> = mutableListOf()
        fields.forEach {
            requestBody.add(it.fieldDescriptor)
        }
        return PayloadDocumentation.relaxedRequestFields(requestBody)
    }

    fun relaxedResponseBody(vararg fields: Field): ResponseFieldsSnippet {
        var responseBody: MutableList<FieldDescriptor> = mutableListOf()
        fields.forEach {
            responseBody.add(it.fieldDescriptor)
        }
        return PayloadDocumentation.relaxedResponseFields(responseBody)
    }
}