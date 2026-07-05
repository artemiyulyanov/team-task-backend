package com.teamtask.dto

import java.time.Instant

data class CalendarEventDto(
    val id: String,
    val title: String,
    val description: String? = null,
    val startAt: Instant,
    val endAt: Instant,
    val eventType: String,     // personal | focus_time
    val createdAt: Instant
)