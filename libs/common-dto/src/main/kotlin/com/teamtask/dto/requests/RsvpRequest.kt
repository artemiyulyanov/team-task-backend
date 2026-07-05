package com.teamtask.dto.requests

import jakarta.validation.constraints.Pattern
import java.time.Instant

data class RsvpRequest(
    @field:Pattern(regexp = "accepted|declined|maybe|tentative")
    val status: String,

    // заполняется только когда status = tentative (3.4.1: "предлагает другое время")
    val proposedStartAt: Instant? = null,
    val proposedEndAt: Instant? = null
)