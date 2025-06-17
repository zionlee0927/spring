package com.ruah.springexample.config

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.time.LocalDate
import com.ruah.springexample.framework.util.EncryptUtil
import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerStatus
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.application.member.domain.vo.Education
import com.ruah.springexample.application.member.domain.vo.Gender
import com.ruah.springexample.application.member.domain.vo.MemberStatus
import com.ruah.springexample.framework.config.security.dto.ManagerDetails
import com.ruah.springexample.framework.config.security.dto.UserType

class TestConstant {

    companion object {
        const val TEST_PASSWORD = "password1"
        val TEST_ENCRYPTED_PASSWORD = EncryptUtil.encryptByArgon2(TEST_PASSWORD)

        const val TEST_MANAGER_ID = "testManager"
        const val TEST_MANAGER_ACCOUNT = "testManager"
        const val TEST_MANAGER_NAME = "테스트매니저"
        const val TEST_MANAGER_EMAIL = "test@beaubrain.bio"
        const val TEST_MANAGER_PHONE = "010-1234-5678"
        const val TEST_MANAGER_TOKEN = "eyJhbGciOiJIUzM4NCJ9.eyJNQU5BR0VSIjp7ImlkIjoidGVzdE1hbmFnZXIiLCJuYW1lIjoi7Jqx64-Z7J20IiwidXNlclR5cGUiOiJNQVNURVIiLCJyb2xlcyI6WyJDRU5URVJfTUFOQUdFIiwiTUFOQUdFUl9NQU5BR0UiLCJNRU1CRVJfTUFOQUdFIiwiSE9NRVdPUktfTUFOQUdFIiwiR1JPVVBfTUFOQUdFIl19LCJleHAiOjE3MTkyNzY2NzR9.F_qof8QI5oo0eJ7KJfTwNM_ftt8kXh_TWej1zRm5P5GOxsgtY5BCiXoL1MQaj1Cj"
        const val TEST_CREDIT_MANAGER_ID = "testCreditManagerId"
        const val TEST_CREDIT_MANAGER_ACCOUNT = "creditManagerAccount"
        const val TEST_CREDIT_MANAGER_NAME = "테스트크레딧매니저"
        const val TEST_CREDIT_MANAGER_PHONE = "010-2929-2929"
        const val TEST_CREDIT_MANAGER_EMAIL = "testCredit@beaubrain.bio"
        val TEST_MANAGER_ROLES = ManagerRole.getManagerRoles().joinToString("//")
        val TEST_MANAGER_STATUS = ManagerStatus.ACTIVE
        val TEST_MANAGER_TYPE = ManagerType.MANAGER

        val TEST_MANAGER_DETAILS = ManagerDetails(
            id = TEST_MANAGER_ID,
            name = TEST_MANAGER_NAME,
            type = UserType.MANAGER,
            role = TEST_MANAGER_ROLES.split("//").map { SimpleGrantedAuthority(it) }
        )

        const val TEST_MASTER_ID = "testMasterId"
        const val TEST_CREDIT_MASTER_ID = "testCreditMasterId"
        const val TEST_MASTER_ACCOUNT = "cybaek"
        val TEST_MASTER_ROLES = ManagerRole.getMasterRoles().joinToString("//")

        const val TEST_MEMBER_ID = "testMemberId"
        const val TEST_CREDIT_MEMBER_ID = "testCreditMemberId"
        const val TEST_MEMBER_ACCOUNT = "testMember"
        const val TEST_MEMBER_NAME = "테스트멤버"
        const val TEST_MEMBER_EMAIL = "testMember@beaubrain.com"
        const val TEST_MEMBER_PHONE = "010-1234-5678"
        const val TEST_MEMBER_TOKEN = "eyJhbGciOiJIUzM4NCJ9.eyJNRU1CRVIiOnsiaWQiOiIwMUhSVjlaTkRQRzdTNjA3R0ZTNUsyRkc1NiIsIm5hbWUiOiLsiJjqsoDsnpAiLCJ1c2VyVHlwZSI6Ik1FTUJFUiIsInJvbGVzIjpbIlJPTEVfTUVNQkVSIl19LCJleHAiOjE3MTk0NTM3Mjd9.FzflkfsGvoif7zdA8rcOnW08dfbzEBk-QFvLeMrBKsk9zFdKPsg7ZTL4YMSvxNg3"
        val TEST_MEMBER_STATUS = MemberStatus.ACTIVE
        val TEST_MEMBER_GENDER = Gender.MALE
        val TEST_MEMBER_EDUCATION = Education.GRADUATE
        val TEST_MEMBER_BIRTHDAY = LocalDate.of(1945, 8, 15)

        const val TEST_AUTHENTICATION_NUMBER = "123456"
    }
}