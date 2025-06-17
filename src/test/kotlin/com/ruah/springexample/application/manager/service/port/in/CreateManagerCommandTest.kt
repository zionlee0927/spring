package com.ruah.springexample.application.manager.service.port.`in`

import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.application.manager.domain.ManagerType
import com.ruah.springexample.framework.exception.BadRequestException
import com.ruah.springexample.framework.exception.ErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CreateManagerCommandTest : FunSpec({

    test("Validation CreateManageCommand") {
        val command = CreateManagerCommand(
            account = "manager",
            name = "매니저1$!@#()",
            email = "email@email.com",
            phone = "010-3456-7890",
            roles = listOf(ManagerRole.ROLE_CENTER_ACCESS),
            type = ManagerType.MANAGER
        )
        command shouldNotBe null

        val invalidAccount = shouldThrow<BadRequestException> { CreateManagerCommand(
            account = "ma",
            name = "매니저",
            email = "email@email.com",
            phone = "010-3456-7890",
            roles = listOf(ManagerRole.ROLE_CENTER_ACCESS),
            type = ManagerType.MANAGER
        )}
        invalidAccount.error shouldBe ErrorCode.INVALID_ACCOUNT

        val invalidName = shouldThrow<BadRequestException> { CreateManagerCommand(
            account = "manager",
            name = "매",
            email = "email@email.com",
            phone = "010-3456-7890",
            roles = listOf(ManagerRole.ROLE_CENTER_ACCESS),
            type = ManagerType.MANAGER
        )}
        invalidName.error shouldBe ErrorCode.INVALID_NAME

    }
})