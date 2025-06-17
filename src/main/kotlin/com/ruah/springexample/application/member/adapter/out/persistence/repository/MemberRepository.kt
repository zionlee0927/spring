package com.ruah.springexample.application.member.adapter.out.persistence.repository

import com.ruah.springexample.application.member.adapter.out.persistence.entity.MemberEntity
import com.ruah.springexample.application.member.adapter.out.persistence.repository.custom.MemberCustomRepository
import com.ruah.springexample.framework.config.jpa.DefaultJpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

interface MemberRepository : DefaultJpaRepository<MemberEntity, String>, MemberCustomRepository {

    fun findByAccount(account: String): MemberEntity?
    fun existsByAccount(account: String): Boolean
    fun existsByPhone(phone: String): Boolean
}