package com.teamtask.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import reactor.util.context.Context

@Component
class InternalAuthFilter(
    @Value("\${internal.auth.secret}") private val internalSecret: String
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val internalHeader = exchange.request.headers.getFirst("X-Internal-Auth")

        if (internalHeader != internalSecret) {
            exchange.response.statusCode = HttpStatus.FORBIDDEN
            return exchange.response.setComplete()
        }

        val userId = exchange.request.headers.getFirst("X-User-Id")

        val mutatedExchange = exchange.mutate()
            .request(exchange.request.mutate().build())
            .build()

        return chain.filter(mutatedExchange)
            .contextWrite(Context.of("userId", userId ?: ""))
    }
}