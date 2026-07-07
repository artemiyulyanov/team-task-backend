package com.teamtask.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtValidator(
    @Value("\${jwt.secret}") private val secret: String
) {
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun validate(token: String): JwtClaims {
        val claims = try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (e: ExpiredJwtException) {
            throw JwtException("Token expired", e)
        } catch (e: JwtException) {
            throw JwtException("Invalid token", e)
        }

        return JwtClaims(
            userId = claims.subject,
            email = claims["email"] as String,
            roles = (claims["roles"] as? List<*>)?.filterIsInstance<String>() ?: emptyList(),
            issuedAt = claims.issuedAt.toInstant(),
            expiresAt = claims.expiration.toInstant()
        )
    }
}