package com.ruah.springexample.framework.config.security.role.aspect

import com.ruah.springexample.application.manager.domain.ManagerRole
import com.ruah.springexample.framework.config.security.role.annotation.ManagerRoleRequired
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.exception.ForbiddenException
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class ManagerRoleCheckAspect(private val jwtUtil: JwtUtil) {

    @Around("@within(requiredRole) || @annotation(requiredRole)")
    fun checkManagerRole(joinPoint: ProceedingJoinPoint, requiredRole: ManagerRoleRequired?): Any? {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val token = getToken(request)
        val getRoles = jwtUtil.getManagerInfo(token).role

        val roleToCheck = getAnnotationRole(joinPoint)

        if (roleToCheck != null && !getRoles.contains(SimpleGrantedAuthority(roleToCheck.name))) {
            throw ForbiddenException()
        }

        return joinPoint.proceed()
    }

    private fun getAnnotationRole(joinPoint: ProceedingJoinPoint): ManagerRole? {
        val methodAnnotation = getMethodAnnotation(joinPoint, ManagerRoleRequired::class.java)
        return methodAnnotation?.role ?: getClassAnnotation(joinPoint, ManagerRoleRequired::class.java)?.role
    }

    private fun <T : Annotation> getMethodAnnotation(joinPoint: ProceedingJoinPoint, annotationClass: Class<T>): T? {
        val method = (joinPoint.signature as MethodSignature).method
        return method.getAnnotation(annotationClass)
    }

    private fun <T : Annotation> getClassAnnotation(joinPoint: ProceedingJoinPoint, annotationClass: Class<T>): T? {
        val targetClass = joinPoint.target.javaClass
        return targetClass.getAnnotation(annotationClass)
    }

    private fun getToken(request: HttpServletRequest): String {
        val headerPrefix = "Bearer "
        val tokenPayload = request.getHeader("Authorization")
        if (tokenPayload.isNullOrEmpty() || tokenPayload.length <= headerPrefix.length) {
            return ""
        }
        return tokenPayload.substring(headerPrefix.length, tokenPayload.length)
    }
}