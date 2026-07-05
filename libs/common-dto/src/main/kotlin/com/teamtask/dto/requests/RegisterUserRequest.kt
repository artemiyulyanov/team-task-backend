package com.teamtask.dto.requests

import com.teamtask.dto.NotificationSettingsDto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterUserRequest(
    @field:Email @field:NotBlank
    val email: String,

    @field:NotBlank @field:Size(min = 8, max = 72) // 72 — лимит bcrypt (п. 9.3 ТЗ)
    val password: String,

    @field:NotBlank @field:Size(max = 100)
    val username: String
)