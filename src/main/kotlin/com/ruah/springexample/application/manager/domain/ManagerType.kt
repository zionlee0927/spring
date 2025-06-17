package com.ruah.springexample.application.manager.domain

import com.ruah.springexample.global.CodeValue

enum class ManagerType(private val description: String): CodeValue {
    MANAGER("매니저"),
    MASTER("마스터");

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }

}