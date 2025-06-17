package com.ruah.springexample.framework.config.security.role.annotation

import com.ruah.springexample.application.manager.domain.ManagerRole


@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ManagerRoleRequired(val role: ManagerRole)
