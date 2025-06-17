package com.ruah.springexample.framework.config.security

import com.ruah.springexample.framework.config.security.filter.CustomAuthenticationFilter
import com.ruah.springexample.framework.config.security.filter.ExceptionHandlerFilter
import com.ruah.springexample.framework.config.security.jwt.JwtAccessDeniedHandler
import com.ruah.springexample.framework.config.security.jwt.JwtAuthenticationEntryPoint
import com.ruah.springexample.framework.config.security.jwt.JwtUtil
import com.ruah.springexample.framework.config.security.provider.AdminTokenProvider
import com.ruah.springexample.framework.config.security.provider.InternalTokenProvider
import com.ruah.springexample.framework.config.security.provider.ManagerTokenProvider
import com.ruah.springexample.framework.config.security.provider.MemberTokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtUtil: JwtUtil,
    @Value("\${service.token.info.internal}")
    private val internalTokenInfo: String,
) {
    val authPassList = listOf(
        AntPathRequestMatcher("/actuator/health", HttpMethod.GET.name()),
        AntPathRequestMatcher("/docs/**", HttpMethod.GET.name()),
        AntPathRequestMatcher("/through/**"),
    )

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(OrRequestMatcher(authPassList))
        }
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/through/**").hasAnyRole("GUEST", "MEMBER", "MANAGER", "ADMIN")
                it.requestMatchers("/common/**").hasAnyRole("MEMBER", "MANAGER", "ADMIN")
                it.requestMatchers("/center/**").hasRole("CENTER_ACCESS")
                it.requestMatchers("/api/**").hasRole("MEMBER")
                it.requestMatchers("/admin/**").hasRole("ADMIN")
                it.requestMatchers("/internal/**").hasRole("INTERNAL")
                it.anyRequest().authenticated()
            }
            .addFilterBefore(ExceptionHandlerFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(
                customAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .exceptionHandling { handler ->
                handler
                    .accessDeniedHandler(JwtAccessDeniedHandler())
                    .authenticationEntryPoint(JwtAuthenticationEntryPoint())
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .headers { it.httpStrictTransportSecurity {} }
            .build()
    }

    fun customAuthenticationFilter(): CustomAuthenticationFilter {
        return CustomAuthenticationFilter(authenticationManager(), jwtUtil)
    }

    @Bean
    protected fun authenticationManager(): AuthenticationManager {
        return ProviderManager(
            ManagerTokenProvider(jwtUtil),
            MemberTokenProvider(jwtUtil),
            AdminTokenProvider(jwtUtil),
            InternalTokenProvider(internalTokenInfo),
        )
    }
}