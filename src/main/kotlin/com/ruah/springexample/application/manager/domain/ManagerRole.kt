package com.ruah.springexample.application.manager.domain

import com.ruah.springexample.global.CodeValue

enum class ManagerRole(private val description: String) : CodeValue {
    ROLE_CENTER_ACCESS("센터 접근 권한"),
    ROLE_MANAGER_MANAGE("매니저관리 권한"),
    ROLE_MEMBER_MANAGE("멤버관리 권한"),
    ROLE_HOMEWORK_MANAGE("과제관리 권한"),
    ROLE_GROUP_MANAGE("그룹관리 권한"),
    ROLE_CHARGE_MANAGE("요금 관리 권한"),
    ;

    companion object {
        fun getMasterRoles(): List<ManagerRole> =
            listOf(ROLE_CENTER_ACCESS, ROLE_MANAGER_MANAGE, ROLE_MEMBER_MANAGE, ROLE_HOMEWORK_MANAGE, ROLE_GROUP_MANAGE, ROLE_CHARGE_MANAGE)
        fun getManagerRoles(): List<ManagerRole> =
            listOf(ROLE_CENTER_ACCESS, ROLE_MEMBER_MANAGE, ROLE_HOMEWORK_MANAGE, ROLE_GROUP_MANAGE)
    }

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}