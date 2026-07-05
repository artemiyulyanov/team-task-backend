package com.teamtask.dto.requests

import jakarta.validation.constraints.Pattern

data class UpdateTaskStatusRequest(
    @field:Pattern(regexp = "todo|in_progress|in_review|done|cancelled")
    val status: String
)