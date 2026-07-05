package com.teamtask.dto

// GET /calendar/day-view — комплексный вид дня (4.2): задачи + встречи + личные события в одной ленте
data class DayViewDto(
    val date: String,          // "2026-07-06"
    val tasks: List<TaskDto> = emptyList(),        // задачи с дедлайном в этот день
    val meetings: List<MeetingDto> = emptyList(),
    val events: List<CalendarEventDto> = emptyList()
)