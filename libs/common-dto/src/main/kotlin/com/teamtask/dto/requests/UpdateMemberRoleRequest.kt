package com.teamtask.dto.requests

import jakarta.validation.constraints.Pattern

data class UpdateMemberRoleRequest(
    @field:Pattern(regexp = "admin|member|viewer")
    val role: String
)