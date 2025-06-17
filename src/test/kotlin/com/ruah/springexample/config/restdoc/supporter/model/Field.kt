package com.ruah.springexample.config.restdoc.supporter.model

import com.ruah.springexample.config.restdoc.supporter.RestDocsAttributeKeys
import com.ruah.springexample.config.restdoc.supporter.RestDocsUtils
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.request.ParameterDescriptor

open class Field(
    val fieldDescriptor: FieldDescriptor,
    val parameterDescriptor: ParameterDescriptor,
) {
    val isIgnored: Boolean = fieldDescriptor.isIgnored
    val isOptional: Boolean = fieldDescriptor.isOptional

    protected open var fieldDescription: String
        get() = this.fieldDescriptor.description as String
        set(value) {
            this.fieldDescriptor.description(value)
        }

    protected open var parameterDescription: String
        get() = this.parameterDescriptor.description as String
        set(value) {
            this.parameterDescriptor.description(value)
        }

    protected open var fieldDefault: String
        get() = this.fieldDescriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_DEFAULT_VALUE, "") as String
        set(value) {
            this.fieldDescriptor.attributes(RestDocsUtils.defaultValue(value))
        }

    protected open var parameterDefault: String
        get() = this.parameterDescriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_DEFAULT_VALUE, "") as String
        set(value) {
            this.parameterDescriptor.attributes(RestDocsUtils.defaultValue(value))
        }

    open var fieldFormat: String
        get() = this.fieldDescriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_FORMAT, "") as String
        set(value) {
            this.fieldDescriptor.attributes(RestDocsUtils.customFormat(value))
        }

    open var parameterFormat: String
        get() = this.parameterDescriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_FORMAT, "") as String
        set(value) {
            this.parameterDescriptor.attributes(RestDocsUtils.customFormat(value))
        }

    protected open var fieldSample: String
        get() = this.fieldDescriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_SAMPLE, "") as String
        set(value) {
            this.fieldDescriptor.attributes(RestDocsUtils.customSample(value))
        }

    protected open var parameterSample: String
        get() = this.parameterDescriptor.attributes.getOrDefault(RestDocsAttributeKeys.KEY_SAMPLE, "") as String
        set(value) {
            this.parameterDescriptor.attributes(RestDocsUtils.customSample(value))
        }

    open infix fun means(value: String): Field {
        this.fieldDescription = value
        this.parameterDescription = value
        return this
    }

    open infix fun attributes(block: Field.() -> Unit): Field {
        block()
        return this
    }

    open infix fun default(value: String): Field {
        this.fieldDefault = value
        this.parameterDefault = value
        return this
    }

    open infix fun formattedAs(value: String): Field {
        this.fieldFormat = value
        this.parameterFormat = value
        return this
    }

    open infix fun example(value: String): Field {
        this.fieldSample = value
        this.parameterSample = value
        return this
    }

    open infix fun isOptional(value: Boolean): Field {
        if (value) {
            fieldDescriptor.optional()
            parameterDescriptor.optional()
        }
        return this
    }

    open infix fun isIgnored(value: Boolean): Field {
        if (value) {
            fieldDescriptor.ignored()
            parameterDescriptor.ignored()
        }
        return this
    }
}