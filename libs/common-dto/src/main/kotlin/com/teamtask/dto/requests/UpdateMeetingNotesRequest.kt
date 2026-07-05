package com.teamtask.dto.requests

import jakarta.validation.constraints.NotBlank

data class UpdateMeetingNotesRequest(
    @field:NotBlank
    val notes: String
)