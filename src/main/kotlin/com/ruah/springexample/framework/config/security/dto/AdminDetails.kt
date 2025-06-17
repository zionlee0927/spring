package com.ruah.springexample.framework.config.security.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AdminDetails(
    val id: String,
    val name: String,
    val type: UserType,
    val role: List<GrantedAuthority>
) : UserDetails {

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