package com.ruah.springexample.config.restdoc.supporter

import org.springframework.restdocs.snippet.Attributes
import org.springframework.restdocs.snippet.Attributes.key


class RestDocsUtils {

    companion object {
        fun defaultValue(value: Any): Attributes.Attribute {
            return key(RestDocsAttributeKeys.KEY_DEFAULT_VALUE).value(value)
        }

        fun customFormat(value: Any): Attributes.Attribute {
            return key(RestDocsAttributeKeys.KEY_FORMAT).value(value)
        }

        fun customSample(value: Any): Attributes.Attribute {
            return key(RestDocsAttributeKeys.KEY_SAMPLE).value(value)
        }

        fun emptyDefaultValue(): Attributes.Attribute {
            return key(RestDocsAttributeKeys.KEY_DEFAULT_VALUE).value(null)
        }

        fun emptyFormat(): Attributes.Attribute {
            return key(RestDocsAttributeKeys.KEY_FORMAT).value(null)
        }

        fun emptySample(): Attributes.Attribute {
            return key(RestDocsAttributeKeys.KEY_SAMPLE).value(null)
        }
    }
}