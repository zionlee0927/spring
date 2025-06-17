package com.ruah.springexample.framework.config.security.jwt

import com.ruah.springexample.framework.config.security.dto.AdminDetails
import com.ruah.springexample.framework.config.security.dto.ManagerDetails
import com.ruah.springexample.framework.config.security.dto.MemberDetails
import com.ruah.springexample.framework.config.security.dto.TokenInfo
import com.ruah.springexample.framework.config.security.dto.UserType
import com.ruah.springexample.framework.exception.UnauthorizedException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtUtil(
    @Value("\${jwt.secret-key.api}")
    private val apiKey: String,
    @Value("\${jwt.secret-key.center}")
    private val centerKey: String,
    @Value("\${jwt.secret-key.admin}")
    private val adminKey: String
) {

    val apiSecretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(apiKey))
    val centerSecretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(centerKey))
    val adminSecretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(adminKey))

    private val managerClaimName = "MANAGER"
    private val memberClaimName = "MEMBER"
    private val adminClaimName = "ADMIN"
    private val accessExpiryTime = 60 * 60 * 1000L // 1 hour
    private val refreshExpiryTime = 30 * 24 * 60 * 60 * 1000L // 1 month

    fun generateManagerToken(tokenInfo: TokenInfo): Pair<String, String> {
        return Pair(generateManagerToken(tokenInfo, accessExpiryTime), generateManagerToken(tokenInfo, refreshExpiryTime))
    }

    fun generateMemberToken(tokenInfo: TokenInfo): Pair<String, String> {
        return Pair(generateMemberToken(tokenInfo, accessExpiryTime), generateMemberToken(tokenInfo, refreshExpiryTime))
    }

    fun generateAdminToken(tokenInfo: TokenInfo):  Pair<String, String> {
        return Pair(generateAdminToken(tokenInfo, accessExpiryTime), generateAdminToken(tokenInfo, refreshExpiryTime))
    }

    fun generateManagerToken(tokenInfo: TokenInfo, expiryTime: Long): String {
        val currentTime = System.currentTimeMillis()

        return Jwts.builder()
            .claim(managerClaimName, tokenInfo)
            .signWith(centerSecretKey)
            .expiration(Date(currentTime + expiryTime))
            .compact()
    }

    fun generateMemberToken(tokenInfo: TokenInfo, expiryTime: Long): String {
        val currentTime = System.currentTimeMillis()

        return Jwts.builder()
            .claim(memberClaimName, tokenInfo)
            .signWith(apiSecretKey)
            .expiration(Date(currentTime + expiryTime))
            .compact()
    }

    fun generateAdminToken(tokenInfo: TokenInfo, expiryTime: Long): String {
        val currentTime = System.currentTimeMillis()

        return Jwts.builder()
            .claim(adminClaimName, tokenInfo)
            .signWith(adminSecretKey)
            .expiration(Date(currentTime + expiryTime))
            .compact()
    }

    fun getClaim(token: String): String {
        val claims = try {
            extractCenterClaims(token)
        } catch (e: UnauthorizedException) {
            try {
                extractApiClaims(token)
            } catch (e: UnauthorizedException) {
                extractAdminClaims(token)
            }
        }

        return when {
            claims.containsKey(managerClaimName) -> managerClaimName
            claims.containsKey(memberClaimName) -> memberClaimName
            claims.containsKey(adminClaimName) -> adminClaimName
            else -> throw UnauthorizedException()
        }
    }

    fun getManagerInfo(token: String): ManagerDetails {
        if (!StringUtils.hasText(token)) {
            return ManagerDetails(
                id = "GUEST",
                name = "GUEST",
                type = UserType.GUEST,
                role = listOf()
            )
        }

        val claims = extractCenterClaims(token)
        val linkedHashMap = claims[managerClaimName] as LinkedHashMap<*, *>
        return linkedHashMap.let {
            val (type, roles) = extractTypeAndRoles(it)
            ManagerDetails(
                id = it["id"].toString(),
                name = it["name"].toString(),
                type = type,
                role = roles.map { SimpleGrantedAuthority(it) }
            )
        }
    }

    fun getMemberInfo(token: String): MemberDetails {
        if (!StringUtils.hasText(token)) {
            return MemberDetails(
                id = "GUEST",
                name = "GUEST",
                type = UserType.GUEST,
                role = listOf()
            )
        }

        val claims = extractApiClaims(token)
        val linkedHashMap = claims[memberClaimName] as LinkedHashMap<*, *>
        return linkedHashMap.let {
            val (type, roles) = extractTypeAndRoles(it)
            MemberDetails(
                id = it["id"].toString(),
                name = it["name"].toString(),
                type = type,
                role = roles.map { SimpleGrantedAuthority(it) }
            )
        }
    }

    fun getAdminInfo(token: String): AdminDetails {
        if (!StringUtils.hasText(token)) {
            return AdminDetails(
                id = "GUEST",
                name = "GUEST",
                type = UserType.GUEST,
                role = listOf()
            )
        }

        val claims = extractAdminClaims(token)
        val linkedHashMap = claims[adminClaimName] as LinkedHashMap<*, *>
        return linkedHashMap.let {
            val (type, roles) = extractTypeAndRoles(it)
            AdminDetails(
                id = it["id"].toString(),
                name = it["name"].toString(),
                type = type,
                role = roles.map { SimpleGrantedAuthority(it) }
            )
        }
    }

    fun getManagerId(token: String): String {
        val claims = extractCenterClaims(token)
        return claims[this.managerClaimName].toString()
    }

    private fun extractCenterClaims(token: String?): Claims {
        return try {
            Jwts.parser()
                .verifyWith(centerSecretKey)
                .build()
                .parseSignedClaims(token)
                .payload

        } catch (ex: JwtException) {
            throw UnauthorizedException()
        }
    }

    private fun extractApiClaims(token: String?): Claims {
        return try {
            Jwts.parser()
                .verifyWith(apiSecretKey)
                .build()
                .parseSignedClaims(token)
                .payload

        } catch (ex: JwtException) {
            throw UnauthorizedException()
        }
    }

    private fun extractAdminClaims(token: String?): Claims {
        return try {
            Jwts.parser()
                .verifyWith(adminSecretKey)
                .build()
                .parseSignedClaims(token)
                .payload

        } catch (ex: JwtException) {
            throw UnauthorizedException()
        }
    }

    private fun extractTypeAndRoles(it: LinkedHashMap<*, *>): Pair<UserType, List<String>> {
        val type = extractUserType(it)
        val roles = extractRoles(it)
        if (type == null || roles.isEmpty()) {
            throw UnauthorizedException()
        }
        return Pair(type, roles)
    }

    private fun extractUserType(it: LinkedHashMap<*, *>) =
        try {
            UserType.valueOf(it["userType"].toString())
        } catch (e: IllegalArgumentException) {
            null
        }

    private fun extractRoles(it: LinkedHashMap<*, *>) = ((it["roles"] as? List<*>)
        ?.filterIsInstance<String>()
        ?.mapNotNull {
            try {
                it
            } catch (e: IllegalArgumentException) {
                null
            }
        }
        ?: emptyList())
}
