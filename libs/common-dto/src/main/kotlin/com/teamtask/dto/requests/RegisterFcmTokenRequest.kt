package com.teamtask.dto.requests

import jakarta.validation.constraints.NotBlank

data class RegisterFcmTokenRequest(
    @field:NotBlank
    val fcmToken: String
)