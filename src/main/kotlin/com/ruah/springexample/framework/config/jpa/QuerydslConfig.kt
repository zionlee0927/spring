package com.ruah.springexample.framework.config.jpa

import com.querydsl.jpa.JPQLTemplates
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QuerydslConfig (
    private val em: EntityManager
){
    @Bean
    fun querydsl(): JPAQueryFactory {
        return JPAQueryFactory(JPQLTemplates.DEFAULT, em)
    }
}
