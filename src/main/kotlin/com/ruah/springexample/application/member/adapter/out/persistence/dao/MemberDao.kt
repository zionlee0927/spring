package com.ruah.springexample.application.member.adapter.out.persistence.dao

import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.application.member.service.port.dto.MemberSearchDto
import com.ruah.springexample.application.member.service.port.`in`.command.SearchMemberCommand
import com.ruah.springexample.application.member.service.port.out.MemberPort
import com.ruah.springexample.application.member.adapter.out.converter.MemberEntityConverter
import com.ruah.springexample.application.member.adapter.out.persistence.repository.MemberRepository
import com.ruah.springexample.framework.exception.NotFoundException
import com.ruah.springexample.framework.wrapper.PageResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberDao(
    private val repository: MemberRepository,
    private val converter: MemberEntityConverter
) : MemberPort {
    @Transactional
    override fun save(dto: MemberDto): MemberDto {
        val entity = converter.toEntity(dto)
        val saved = repository.save(entity)
        return converter.toDto(saved)
    }

    @Transactional(readOnly = true)
    override fun search(command: SearchMemberCommand): PageResponse<MemberSearchDto> {
        return repository.search(command)
    }

    @Transactional(readOnly = true)
    override fun get(memberId: String): MemberDto {
        val entity = repository.findByIdOrNull(memberId)
            ?: throw NotFoundException("회원 정보 없음. id - $memberId")

        return converter.toDto(entity)
    }

    override fun getByAccount(account: String): MemberDto {
        val entity = repository.findByAccount(account)
            ?: throw NotFoundException("회원 정보 없음. account - $account")
        return converter.toDto(entity)
    }

    @Transactional(readOnly = true)
    override fun existsByAccount(account: String): Boolean {
        return repository.existsByAccount(account)
    }

    @Transactional(readOnly = true)
    override fun existsByPhone(phone: String): Boolean {
        return repository.existsByPhone(phone)
    }
}