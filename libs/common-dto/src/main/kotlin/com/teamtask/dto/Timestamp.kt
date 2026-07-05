package com.teamtask.dto

import java.time.Instant

interface Timestamp {
    val createdAt: Instant
    val updatedAt: Instant
}