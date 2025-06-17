package com.ruah.springexample.framework.config.security.dto

import com.ruah.springexample.application.member.domain.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails(
    val id: String,
    val name: String,
    val type: UserType,
    val role: List<GrantedAuthority>
) : UserDetails {

    constructor(member: Member): this(
        id = member.id,
        name = member.name,
        type = UserType.MEMBER,
        role = UserType.MEMBER.getRoles().map { SimpleGrantedAuthority(it) }
    )

    constructor(id: String, name: String): this(
        id = id,
        name = name,
        type = UserType.MANAGER,
        role = UserType.MEMBER.getRoles().map { SimpleGrantedAuthority(it) }
    )

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return role
    }

    override fun getPassword(): String {
        return ""
    }

    override fun getUsername(): String {
        return id
    }

    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}