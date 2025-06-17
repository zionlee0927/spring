package com.ruah.springexample.framework.config.mapstruct

import com.ruah.springexample.framework.wrapper.vo.SingleVo
import org.mapstruct.Mapper
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface SingleValueConverter {

    companion object {
        @JvmStatic
        @Named("objectToValue")
        fun <V> toValue(obj : SingleVo<V>) : V {
            return obj.value
        }
    }
}