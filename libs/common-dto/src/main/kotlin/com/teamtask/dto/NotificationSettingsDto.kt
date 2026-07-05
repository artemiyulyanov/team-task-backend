package com.teamtask.dto

data class NotificationSettingsDto(
    val taskAssigned: NotificationChannelPref = NotificationChannelPref(push = true, inApp = true),
    val taskStatusChanged: NotificationChannelPref = NotificationChannelPref(push = false, inApp = true),
    val commentMention: NotificationChannelPref = NotificationChannelPref(push = true, inApp = true),
    val deadlineApproaching: NotificationChannelPref = NotificationChannelPref(push = true, inApp = false),
    val meetingInvite: NotificationChannelPref = NotificationChannelPref(push = true, inApp = true),
    val meetingReminder: NotificationChannelPref = NotificationChannelPref(push = true, inApp = false),
    val morningDigest: DigestPref = DigestPref(enabled = true, time = "08:00")
)

data class NotificationChannelPref(
    val push: Boolean,
    val inApp: Boolean
)

data class DigestPref(
    val enabled: Boolean,
    val time: String   
)