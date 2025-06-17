package com.ruah.springexample.framework.config.security.token

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class InternalPreProcessingToken(
    principal: String,
    credentials: Int
) : UsernamePasswordAuthenticationToken(principal, credentials)
