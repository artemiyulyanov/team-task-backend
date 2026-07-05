package com.teamtask.dto.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class InviteMemberRequest(
    @field:jakarta.validation.constraints.Email @field:NotBlank
    val email: String,

    @field:Pattern(regexp = "admin|member|viewer") // owner не назначается инвайтом
    val role: String = "member"
)