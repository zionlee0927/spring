import com.ruah.springexample.application.authentication.adapter.out.persistence.entity.AuthenticationEntity
import com.ruah.springexample.application.authentication.domain.AuthenticationPlatformType
import com.ruah.springexample.application.authentication.service.port.dto.AuthenticationDto
import com.ruah.springexample.config.TestConstant.Companion.TEST_AUTHENTICATION_NUMBER
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_EMAIL
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_ID
import com.ruah.springexample.config.TestConstant.Companion.TEST_MANAGER_TOKEN
import java.time.LocalDateTime

fun makeAuthenticationDto(
    id: String = TEST_MANAGER_ID,
    platformType: AuthenticationPlatformType = AuthenticationPlatformType.EMAIL,
    target: String = TEST_MANAGER_EMAIL,
    number: String = TEST_AUTHENTICATION_NUMBER,
    createdAt: LocalDateTime = LocalDateTime.now(),
    token: String = TEST_MANAGER_TOKEN
) : AuthenticationDto {
    return AuthenticationDto(
        id = id,
        platformType = platformType,
        target = target,
        number = number,
        createdAt = createdAt,
        token = token
    )
}

fun makeAuthenticationEntity(
    id: String = TEST_MANAGER_ID,
    platformType: AuthenticationPlatformType = AuthenticationPlatformType.EMAIL,
    target: String = TEST_MANAGER_EMAIL,
    number: String = TEST_AUTHENTICATION_NUMBER,
    token: String = TEST_MANAGER_TOKEN
) : AuthenticationEntity {
    return AuthenticationEntity(
        id = id,
        platformType = platformType,
        target = target,
        number = number,
        token = token,
        createdAt = LocalDateTime.now()
    )
}
