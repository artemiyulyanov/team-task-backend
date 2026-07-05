package com.teamtask.dto.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateProjectRequest(
    @field:NotBlank @field:Size(max = 100)
    val name: String,

    val description: String? = null,

    @field:Pattern(regexp = "^#[0-9A-Fa-f]{6}$")
    val color: String? = null,

    val dueDate: LocalDate? = null
)