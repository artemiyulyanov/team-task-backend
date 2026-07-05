package com.teamtask.dto.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.Instant

data class CreateMeetingRequest(
    val projectId: String? = null,

    @field:NotBlank
    val title: String,
    val description: String? = null,

    val startAt: Instant,
    val endAt: Instant,
    val location: String? = null,
    val recurrenceRule: String? = null,

    val participantIds: List<String> = emptyList(),
    val participantEmails: List<String> = emptyList(), // приглашение не-участников проекта по email (3.4.1)
    val agendaTaskIds: List<String> = emptyList()
)

