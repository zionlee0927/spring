package com.ruah.springexample.framework.wrapper

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort


class PageRequest(
    page: Int? = DEFAULT_PAGE,
    size: Int? = DEFAULT_SIZE
) : Pageable {
    var page: Int
    var size: Int

    init {
        this.page = getPage(page)
        this.size = getSize(size)
    }

    private fun getPage(page: Int?): Int {
        return page?.coerceAtLeast(MIN_PAGE) ?: DEFAULT_PAGE
    }

    private fun getSize(size: Int?): Int {
        return size?.coerceIn(MIN_SIZE, MAX_SIZE) ?: DEFAULT_SIZE
    }

    fun of(): Pageable {
        return PageRequest.of(page - 1, size)
    }

    companion object {
        const val DEFAULT_PAGE = 1
        const val MIN_PAGE = 1
        const val DEFAULT_SIZE = 20
        const val MIN_SIZE = 1
        const val MAX_SIZE = 50
    }

    override fun getPageNumber(): Int {
        return page
    }

    override fun getPageSize(): Int {
        return size
    }

    override fun getOffset(): Long {
        return (page.toLong() - 1L) * size.toLong()
    }

    override fun getSort(): Sort {
        return Sort.unsorted()
    }

    override fun next(): Pageable {
        return PageRequest(page + 1, size)
    }

    override fun previousOrFirst(): Pageable {
        return if (page > 1) {
            PageRequest( page - 1, size)
        } else {
            PageRequest( 1, size)
        }
    }

    override fun first(): Pageable {
        return PageRequest( 1, size)
    }

    override fun withPage(pageNumber: Int): Pageable {
        return PageRequest(pageNumber, size)
    }

    override fun hasPrevious(): Boolean {
        return page > 1
    }
}