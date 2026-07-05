package com.teamtask.dto.requests

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.Instant

data class CreateTaskRequest(
    @field:NotBlank @field:Size(max = 255)
    val title: String,

    @field:Size(max = 10_000)
    val description: String? = null,

    @field:Pattern(regexp = "low|medium|high|critical")
    val priority: String? = null,

    val dueDate: Instant? = null,

    @field:DecimalMin("0")
    val estimatedHours: BigDecimal? = null,

    val parentTaskId: String? = null,
    val assigneeIds: List<String> = emptyList(),
    val tagIds: List<String> = emptyList()
)