package com.teamtask.dto.requests

import java.time.Instant

data class UpdateCalendarEventRequest(
    val title: String? = null,
    val description: String? = null,
    val startAt: Instant? = null,
    val endAt: Instant? = null
)