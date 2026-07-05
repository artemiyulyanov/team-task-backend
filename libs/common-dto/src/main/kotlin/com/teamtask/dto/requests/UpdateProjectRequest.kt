package com.teamtask.dto.requests

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UpdateProjectRequest(
    @field:Size(max = 100)
    val name: String? = null,
    val description: String? = null,

    @field:Pattern(regexp = "^#[0-9A-Fa-f]{6}$")
    val color: String? = null,

    val dueDate: LocalDate? = null,

    @field:Pattern(regexp = "active|archived|deleted")
    val status: String? = null
)