package com.teamtask.dto

data class ErrorResponse(
    val error: ErrorDetail
)

data class ErrorDetail(
    val code: String,
    val message: String,
    val details: Any? = null
)

object ErrorCode {
    const val VALIDATION_ERROR = "VALIDATION_ERROR"   // 400/422 — Jakarta Validation не прошла
    const val UNAUTHORIZED = "UNAUTHORIZED"           // 401 — нет/невалиден JWT
    const val FORBIDDEN = "FORBIDDEN"                 // 403 — роль не даёт доступа (3.2.2)
    const val NOT_FOUND = "NOT_FOUND"                 // 404
    const val CONFLICT = "CONFLICT"                   // 409 — например, дублирующийся email
    const val RATE_LIMITED = "RATE_LIMITED"           // 429 (9.3: 100 req/min, 5 req/5min на /auth/login)
    const val INTERNAL_ERROR = "INTERNAL_ERROR"       // 500
}