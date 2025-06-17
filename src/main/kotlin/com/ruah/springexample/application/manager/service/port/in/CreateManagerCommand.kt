package com.ruah.springexample.application.manager.service.port.`in`

import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode

data class CreateManagerCommand(
    val account: String,
    val name: String,
    val email: String,
    val phone: String,
    val type: ManagerType,
    var roles: List<ManagerRole>,
) {
    init {
        roles = when(type) {
            ManagerType.MASTER -> ManagerRole.getMasterRoles()
            ManagerType.MANAGER -> ManagerRole.getManagerRoles()
        }
        validAccount()
        validName()
        validEmail()
        validContract()
    }

    private fun validAccount() {
        val accountPattern = """^[a-zA-Z0-9]{4,12}""".toRegex()
        if (!accountPattern.matches(account)) {
            throw BadRequestException(ErrorCode.INVALID_ACCOUNT)
        }
    }

    private fun validName() {
        val namePattern = """^[a-zA-Z0-9가-힣!@#${'$'}%^&*()]{2,12}""".toRegex()
        if (!namePattern.matches(name)) {
            throw BadRequestException(ErrorCode.INVALID_NAME)
        }
    }

    private fun validEmail() {
        val emailPattern = """^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}""".toRegex()
        if (!emailPattern.matches(email)) {
            throw BadRequestException("Invalid email format")
        }
    }

    private fun validContract() {
        val phonePattern = """^010-[0-9]{4}-[0-9]{4}""".toRegex()
        if (!phonePattern.matches(phone)) {
            throw BadRequestException("Invalid phone number")
        }
    }
}