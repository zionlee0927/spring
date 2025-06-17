package com.ruah.springexample.application.member.adapter.out.persistence.repository.custom

import com.ruah.springexample.application.member.domain.vo.MemberStatus
import com.ruah.springexample.application.member.service.port.dto.MemberSearchDto
import com.ruah.springexample.application.member.service.port.`in`.command.SearchMemberCommand
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import com.ruah.springexample.application.member.adapter.out.persistence.entity.QMemberEntity.memberEntity
import com.ruah.springexample.application.member.service.port.dto.QMemberSearchDto
import com.ruah.springexample.framework.wrapper.PageResponse
import io.micrometer.common.util.StringUtils
import org.springframework.stereotype.Repository

@Repository
class MemberCustomRepositoryImpl(
    private val factory: JPAQueryFactory
): MemberCustomRepository {
    override fun search(command: SearchMemberCommand): PageResponse<MemberSearchDto> {
        val memberList = searchList(command)
            .select(
                QMemberSearchDto(
                    memberEntity.id,
                    memberEntity.status,
                    memberEntity.name,
                    memberEntity.phone,
                    memberEntity.birthday,
                    memberEntity.gender,
                    memberEntity.education
                )
            )
            .fetch()

        val count = whereCondition(command)
            .select(memberEntity.count())
            .fetchOne() ?: 0L

        return PageResponse(memberList, count.toInt())
    }

    private fun searchList(command: SearchMemberCommand): JPAQuery<*> {
        return whereCondition(command)
            .orderBy(memberEntity.name.asc())
            .offset(command.page.offset)
            .limit(command.page.size.toLong())
    }

    private fun whereCondition(command: SearchMemberCommand): JPAQuery<*> {
        return factory
            .from(memberEntity)
            .where(
                eqAccount(command.account),
                likeName(command.name),
                memberEntity.status.eq(MemberStatus.ACTIVE)
            )
    }

    private fun eqAccount(account: String?): BooleanExpression? {
        return if (account == null) null else memberEntity.account.eq(account)
    }

    private fun likeName(name: String?): BooleanExpression? {
        return if (StringUtils.isBlank(name)) {
            null
        } else {
            memberEntity.name.contains(name)
        }
    }
}