package com.teamtask.dto.requests

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.Instant

data class UpdateTaskRequest(
    @field:Size(max = 255)
    val title: String? = null,

    @field:Size(max = 10_000)
    val description: String? = null,

    @field:Pattern(regexp = "low|medium|high|critical")
    val priority: String? = null,

    val dueDate: Instant? = null,
    val estimatedHours: BigDecimal? = null,

    val assigneeIds: List<String>? = null,   // null = не менять, [] = очистить всех
    val tagIds: List<String>? = null
)