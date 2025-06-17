package com.ruah.springexample.application.manager.adapter.out.persistence.repository.custom

import com.ruah.springexample.application.manager.domain.ManagerStatus
import com.ruah.springexample.application.manager.service.port.`in`.SearchManagerCommand
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.adapter.out.persistence.entity.QManagerEntity.managerEntity
import org.springframework.stereotype.Repository

@Repository
class ManagerCustomRepositoryImpl(
    private val factory: JPAQueryFactory
) : ManagerCustomRepository {
    override fun search(command: SearchManagerCommand): List<ManagerEntity> {
        return factory
            .select(managerEntity)
            .from(managerEntity)
            .where(
                eqAccount(command.account),
                eqStatus(command.status),
                eqName(command.name),
                eqEmail(command.email),
                eqPhone(command.phone)
            )
            .fetch()
    }

    private fun eqAccount(account: String?): BooleanExpression? {
        return account?.let { managerEntity.account.eq(account) }
    }

    private fun eqStatus(status: ManagerStatus?): BooleanExpression? {
        return status?.let { managerEntity.status.eq(status) }
    }

    private fun eqName(name: String?): BooleanExpression? {
        return name?.let { managerEntity.name.eq(name) }
    }

    private fun eqEmail(email: String?): BooleanExpression? {
        return email?.let { managerEntity.email.eq(email) }
    }

    private fun eqPhone(phone: String?): BooleanExpression? {
        return phone?.let { managerEntity.phone.eq(phone) }
    }
}