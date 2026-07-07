package com.teamtask.gateway.jwt

import com.teamtask.jwt.JwtValidator
import io.jsonwebtoken.JwtException
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationGatewayFilterFactory(
    private val jwtValidator: JwtValidator,
    @Value("\${internal.auth.secret}") private val internalSecret: String
) : AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config>(Config::class.java) {

    class Config

    override fun apply(config: Config): GatewayFilter = GatewayFilter { exchange, chain ->
        val token = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
            ?.removePrefix("Bearer ")

        if (token == null) {
            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
            return@GatewayFilter exchange.response.setComplete()
        }

        try {
            val claims = jwtValidator.validate(token)
            val mutatedRequest = exchange.request.mutate()
                .header("X-User-Id", claims.userId)
                .header("X-Internal-Auth", internalSecret)
                .build()
            chain.filter(exchange.mutate().request(mutatedRequest).build())
        } catch (e: JwtException) {
            exchange.response.statusCode = HttpStatus.UNAUTHORIZED
            exchange.response.setComplete()
        }
    }
}