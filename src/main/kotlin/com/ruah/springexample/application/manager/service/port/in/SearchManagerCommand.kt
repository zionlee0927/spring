package com.ruah.springexample.application.manager.service.port.`in`

import com.ruah.springexample.application.manager.domain.ManagerStatus

data class SearchManagerCommand(
    val account: String? = null,
    val status: ManagerStatus? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null
)
