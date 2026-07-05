package com.teamtask.dto

import java.time.Instant

data class MeetingDto(
    override val id: String,
    val projectId: String? = null,   // null — личная встреча, не привязанная к проекту
    val organizer: UserPublicDto,

    val title: String,
    val description: String? = null,

    val startAt: Instant,
    val endAt: Instant,
    val location: String? = null,     // адрес или URL видеосвязи
    val recurrenceRule: String? = null, // iCal RRULE

    val status: String,               // scheduled | cancelled | completed

    val participants: List<MeetingParticipantDto> = emptyList(),
    val agendaTasks: List<TaskSummaryDto> = emptyList(), // прикреплённые задачи (3.4.1)

    val myRsvpStatus: String,         // удобно для клиента — не искать себя в participants

    val createdAt: Instant
) : Identified

data class MeetingParticipantDto(
    val user: UserPublicDto,
    val status: String,               // pending | accepted | declined | maybe | tentative
    val proposedStartAt: Instant? = null,
    val proposedEndAt: Instant? = null,
    val respondedAt: Instant? = null
)

data class MeetingNotesDto(
    val meetingId: String,
    val notes: String,
    val updatedAt: Instant
)