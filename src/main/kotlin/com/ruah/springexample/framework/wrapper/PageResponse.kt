package com.ruah.springexample.framework.wrapper

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.domain.Pageable
import kotlin.math.ceil

class PageResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var data: List<T>

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var totalNumber: Int? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var total: Int

    constructor(data: List<T>, totalElement: Int) {
        this.data = data
        this.total = totalElement
    }

    constructor(data: List<T>, totalElement: Long) {
        this.data = data
        this.total = totalElement.toInt()
    }

    constructor(data: List<T>, pageable: Pageable, totalElement: Int) {
        this.data = data
        this.total = totalElement
        this.totalNumber = calculateTotalNumber(pageable, totalElement)
    }

    constructor(data: List<T>, pageable: Pageable, totalElement: Long) {
        this.data = data
        this.total = totalElement.toInt()
        this.totalNumber = calculateTotalNumber(pageable, totalElement.toInt())
    }

    private fun calculateTotalNumber(pageable: Pageable, totalElement: Int) =
        if (pageable.pageSize == 0) 1 else ceil(totalElement.toDouble() / pageable.pageSize.toDouble()).toInt()
}
