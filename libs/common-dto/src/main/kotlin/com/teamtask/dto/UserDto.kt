package com.teamtask.dto

import java.time.Instant

data class UserDto(
    override val id: Identifier,
    val email: String,
    val username: String,
    val avatarUrl: String? = null,
    val timezone: String,
    val notificationSettings: NotificationSettingsDto = NotificationSettingsDto(),
    override val createdAt: Instant,
    override val updatedAt: Instant,
) : Timestamp, Identified

data class UserPublicDto(
    override val id: String,
    val displayName: String,
    val avatarUrl: String? = null
) : Identified