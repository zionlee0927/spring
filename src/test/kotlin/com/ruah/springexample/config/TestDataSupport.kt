package com.ruah.springexample.config

import com.ruah.springexample.config.util.makeManagerEntity
import com.ruah.springexample.application.manager.adapter.out.converter.ManagerEntityConverter
import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.adapter.out.persistence.repository.ManagerRepository
import com.ruah.springexample.application.manager.domain.Manager
import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.application.manager.service.converter.ManagerConverter
import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import com.ruah.springexample.application.member.adapter.out.converter.MemberEntityConverter
import com.ruah.springexample.application.member.adapter.out.persistence.entity.MemberEntity
import com.ruah.springexample.application.member.adapter.out.persistence.repository.MemberRepository
import com.ruah.springexample.application.member.domain.Member
import com.ruah.springexample.application.member.service.converter.MemberConverter
import com.ruah.springexample.application.member.service.port.dto.MemberDto
import com.ruah.springexample.config.TestConstant.Companion.TEST_CREDIT_MANAGER_ACCOUNT
import com.ruah.springexample.config.TestConstant.Companion.TEST_CREDIT_MANAGER_EMAIL
import com.ruah.springexample.config.TestConstant.Companion.TEST_CREDIT_MANAGER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_CREDIT_MANAGER_NAME
import com.ruah.springexample.config.TestConstant.Companion.TEST_CREDIT_MANAGER_PHONE
import com.ruah.springexample.config.TestConstant.Companion.TEST_CREDIT_MASTER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_CREDIT_MEMBER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_MASTER_ACCOUNT
import com.ruah.springexample.config.TestConstant.Companion.TEST_MASTER_ID
import com.ruah.springexample.config.util.makeMemberEntity
import com.ruah.springexample.framework.config.security.dto.UserType
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import org.springframework.stereotype.Component

@Component
class TestDataSupport(
    private val jwtUtil: JwtUtil,
    private val managerRepository: ManagerRepository,
    private val managerConverter: ManagerConverter,
    private val managerEntityConverter: ManagerEntityConverter,
    private val memberRepository: MemberRepository,
    private val memberConverter: MemberConverter,
    private val memberEntityConverter: MemberEntityConverter,
) {

    private final var testManagerEntity: ManagerEntity
    final var testManagerDto: ManagerDto
    private final var testManager: Manager
    final var testManagerToken: String
    private final var testMasterEntity: ManagerEntity
    final var testMasterDto: ManagerDto
    private final var testMaster: Manager
    final var testMasterToken: String
    private final var testMemberEntity: MemberEntity
    final var testMemberDto: MemberDto
    private final var testMember: Member
    final var testMemberToken: String

    private final var testCreditManagerEntity: ManagerEntity
    final var testCreditManagerDto: ManagerDto
    private final var testCreditManager: Manager
    final var testCreditManagerToken: String
    private final var testCreditMasterEntity: ManagerEntity
    final var testCreditMasterDto: ManagerDto
    private final var testCreditMaster: Manager
    final var testCreditMasterToken: String
    private final var testCreditMemberEntity: MemberEntity
    final var testCreditMemberDto: MemberDto
    private final var testCreditMember: Member
    final var testCreditMemberToken: String

    final var testInternalToken: String


    init {
        managerRepository.deleteAll()

        testManagerEntity = createTestManager(makeManagerEntity(id = TEST_MANAGER_ID))
        testManagerDto = managerEntityConverter.toDto(testManagerEntity)
        testManager = managerConverter.toDomain(testManagerDto)
        val managerTokenInfo = managerConverter.toTokenInfo(testManager)
        testManagerToken = "Bearer ${jwtUtil.generateManagerToken(managerTokenInfo).second}"

        testCreditManagerEntity = createTestManager(
            makeManagerEntity(
                id = TEST_CREDIT_MANAGER_ID,
                account = TEST_CREDIT_MANAGER_ACCOUNT,
                name = TEST_CREDIT_MANAGER_NAME,
                email = TEST_CREDIT_MANAGER_EMAIL,
                phone = TEST_CREDIT_MANAGER_PHONE,
            )
        )
        testCreditManagerDto = managerEntityConverter.toDto(testCreditManagerEntity)
        testCreditManager = managerConverter.toDomain(testCreditManagerDto)
        val creditManagerTokenInfo = managerConverter.toTokenInfo(testCreditManager)
        testCreditManagerToken = "Bearer ${jwtUtil.generateManagerToken(creditManagerTokenInfo).second}"

        testMasterEntity = createTestManager(
            makeManagerEntity(
                id = TEST_MASTER_ID,
                account = TEST_MASTER_ACCOUNT,
                type = ManagerType.MASTER,
                roles = ManagerRole.getMasterRoles(),
                phone = "010-1234-5678",
                email = "master@email.com"
            )
        )
        testMasterDto = managerEntityConverter.toDto(testMasterEntity)
        testMaster = managerConverter.toDomain(testMasterDto)
        val masterTokenInfo = managerConverter.toTokenInfo(testMaster)
        testMasterToken = "Bearer ${jwtUtil.generateManagerToken(masterTokenInfo).second}"

        testCreditMasterEntity = createTestManager(
            makeManagerEntity(
                id = TEST_CREDIT_MASTER_ID,
                account = "creditMaster",
                type = ManagerType.MASTER,
                roles = ManagerRole.getMasterRoles(),
                phone = "010-1234-5678",
                email = "creditMaster@email.com",
            )
        )
        testCreditMasterDto = managerEntityConverter.toDto(testCreditMasterEntity)
        testCreditMaster = managerConverter.toDomain(testCreditMasterDto)
        val creditMasterTokenInfo = managerConverter.toTokenInfo(testCreditMaster)
        testCreditMasterToken = "Bearer ${jwtUtil.generateManagerToken(creditMasterTokenInfo).second}"

        testMemberEntity = createTestMember(makeMemberEntity())
        testMemberDto = memberEntityConverter.toDto(testMemberEntity)
        testMember = memberConverter.toDomain(testMemberDto)
        val memberTokenInfo = memberConverter.toTokenInfo(testMember, UserType.MEMBER)
        testMemberToken = "Bearer ${jwtUtil.generateMemberToken(memberTokenInfo).second}"

        testCreditMemberEntity = createTestMember(
            makeMemberEntity(
                id = TEST_CREDIT_MEMBER_ID,
                account = "creditMember",
                managerId = testCreditManagerEntity.id
            )
        )
        testCreditMemberDto = memberEntityConverter.toDto(testCreditMemberEntity)
        testCreditMember = memberConverter.toDomain(testCreditMemberDto)
        val creditMemberTokenInfo = memberConverter.toTokenInfo(testCreditMember, UserType.MEMBER)
        testCreditMemberToken = "Bearer ${jwtUtil.generateMemberToken(creditMemberTokenInfo).second}"

        testInternalToken = "Bearer 9d8f246f-61e3-4a25-901d-6623e1e83605"

    }

    private fun createTestManager(entity: ManagerEntity): ManagerEntity {
        return managerRepository.save(entity)
    }

    private fun createTestMember(entity: MemberEntity): MemberEntity {
        return memberRepository.save(entity)
    }

}
