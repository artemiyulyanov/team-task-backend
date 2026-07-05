package com.teamtask.dto.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.Instant

data class CreateCalendarEventRequest(
    @field:NotBlank
    val title: String,
    val description: String? = null,
    val startAt: Instant,
    val endAt: Instant,

    @field:Pattern(regexp = "personal|focus_time")
    val eventType: String = "personal"
)