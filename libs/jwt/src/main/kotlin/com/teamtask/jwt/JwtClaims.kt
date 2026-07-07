package com.teamtask.jwt

import java.time.Instant

data class JwtClaims(
    val userId: String,
    val email: String,
    val roles: List<String> = emptyList(),
    val issuedAt: Instant,
    val expiresAt: Instant
)