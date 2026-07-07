package com.teamtask.gateway.configuration

import com.teamtask.gateway.jwt.JwtAuthenticationGatewayFilterFactory
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
    private val jwtAuthFilterFactory: JwtAuthenticationGatewayFilterFactory
) {
    @Bean
    fun routes(builder: RouteLocatorBuilder): RouteLocator = builder.routes()
        .route("auth-service") { r ->
            r.path("/api/auth/**")
                .filters { f -> f.stripPrefix(1) }
                .uri("lb://auth-service")
        }
        .route("task-service") { r ->
            r.path("/api/tasks/**")
                .filters { f ->
                    f.stripPrefix(1)
                        .filter(jwtAuthFilterFactory.apply(JwtAuthenticationGatewayFilterFactory.Config()))
                }
                .uri("lb://task-service")
        }
        .build()
}