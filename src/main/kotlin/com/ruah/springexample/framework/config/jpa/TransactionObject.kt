package com.ruah.springexample.framework.config.jpa

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

object TransactionObject {
    private lateinit var transactionRunner: TransactionRunner

    fun initialize(transactionRunner: TransactionRunner) {
        TransactionObject.transactionRunner = transactionRunner
    }

    fun <T> runTx(function: () -> T): T = transactionRunner.runTx(function)

    fun <T> runReadOnlyTx(function: () -> T): T = transactionRunner.runReadOnly(function)
}

@Configuration
class TransactionConfig {
    @Bean
    fun txInitBean(transactionRunner: TransactionRunner): InitializingBean =
        InitializingBean { TransactionObject.initialize(transactionRunner) }
}

@Component
class TransactionRunner {
    @Transactional
    fun <T> runTx(block: () -> T): T = block()

    @Transactional(readOnly = true)
    fun <T> runReadOnly(block: () -> T): T = block()
}