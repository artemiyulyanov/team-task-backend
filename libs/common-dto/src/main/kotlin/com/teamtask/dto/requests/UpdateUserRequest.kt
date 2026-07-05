package com.teamtask.dto.requests

import com.teamtask.dto.NotificationSettingsDto
import jakarta.validation.constraints.Size

data class UpdateUserRequest(
    @field:Size(max = 100)
    val username: String? = null,

    val avatarUrl: String? = null,

    val timezone: String? = null,  // валидация через ZoneId.of() в сервисе — см. предыдущий ответ

    val notificationSettings: NotificationSettingsDto? = null
)