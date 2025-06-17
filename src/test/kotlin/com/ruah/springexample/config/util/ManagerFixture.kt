package com.ruah.springexample.config.util

import com.ruah.springexample.application.manager.adapter.out.persistence.entity.ManagerEntity
import com.ruah.springexample.application.manager.domain.Manager
import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerStatus
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.application.manager.domain.vo.ManagerPassword
import com.ruah.springexample.application.manager.service.port.dto.ManagerDto
import com.ruah.springexample.config.TestConstant.Companion.TEST_ENCRYPTED_PASSWORD
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ACCOUNT
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_EMAIL
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_NAME
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_PHONE
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_STATUS
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_TYPE
import com.ruah.springexample.framework.config.security.dto.TokenInfo
import com.ruah.springexample.framework.config.security.dto.UserType
import ulid.ULID
import java.time.LocalDateTime

fun makeManager(
    id: String = TEST_MANAGER_ID,
    account: String = TEST_MANAGER_ACCOUNT,
    password: String = TEST_ENCRYPTED_PASSWORD,
    name: String = TEST_MANAGER_NAME,
    status: ManagerStatus = TEST_MANAGER_STATUS,
    email: String = TEST_MANAGER_EMAIL,
    phone: String = TEST_MANAGER_PHONE,
    roles: List<ManagerRole> = listOf(ManagerRole.ROLE_CENTER_ACCESS),
    type: ManagerType = TEST_MANAGER_TYPE,
    createdAt: LocalDateTime = LocalDateTime.now()
): Manager {
    return Manager(
        id = id,
        account = account,
        password = ManagerPassword(password),
        name = name,
        status = status,
        email = email,
        phone = phone,
        roles = roles,
        type = type,
        createdAt = createdAt
    )
}

fun makeManagerDto(
    id: String = TEST_MANAGER_ID,
    account: String = TEST_MANAGER_ACCOUNT,
    password: ManagerPassword = ManagerPassword(TEST_ENCRYPTED_PASSWORD),
    name: String = TEST_MANAGER_NAME,
    status: ManagerStatus = TEST_MANAGER_STATUS,
    email: String = TEST_MANAGER_EMAIL,
    phone: String = TEST_MANAGER_PHONE,
    roles: List<ManagerRole> = listOf(ManagerRole.ROLE_CENTER_ACCESS),
    type: ManagerType = TEST_MANAGER_TYPE,
    createdAt: LocalDateTime = LocalDateTime.now()
): ManagerDto {
    return ManagerDto(
        id = id,
        account = account,
        password = password,
        name = name,
        status = status,
        email = email,
        phone = phone,
        roles = roles,
        type = type,
        createdAt = createdAt
    )
}

fun makeManagerEntity(
    id: String = ULID.randomULID(),
    account: String = TEST_MANAGER_ACCOUNT,
    password: String = TEST_ENCRYPTED_PASSWORD,
    name: String = TEST_MANAGER_NAME,
    status: ManagerStatus = TEST_MANAGER_STATUS,
    email: String = TEST_MANAGER_EMAIL,
    phone: String = TEST_MANAGER_PHONE,
    roles: List<ManagerRole> = ManagerRole.getManagerRoles(),
    type: ManagerType = TEST_MANAGER_TYPE
): ManagerEntity {
    return ManagerEntity(
        id = id,
        account = account,
        password = password,
        name = name,
        status = status,
        email = email,
        phone = phone,
        roles = roles.joinToString("//") { it.name },
        type = type,
        createdAt = LocalDateTime.now()
    )
}

fun makeTokenInfo(
    id: String = TEST_MANAGER_ID,
    name: String = TEST_MANAGER_NAME,
    userType: UserType = UserType.MANAGER,
    roles: Array<String> = ManagerRole.getManagerRoles().map { it.name }.toTypedArray()
): TokenInfo {
    return TokenInfo(
        id = id,
        name = name,
        userType = userType,
        roles = roles
    )
}
