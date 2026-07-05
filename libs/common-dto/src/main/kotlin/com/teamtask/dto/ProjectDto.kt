package com.teamtask.dto

import java.time.Instant
import java.time.LocalDate

data class ProjectDto(
    override val id: String,
    val name: String,
    val description: String? = null,
    val color: String,           
    val owner: UserPublicDto,
    val status: String,          
    val dueDate: LocalDate? = null,

    val memberCount: Int = 0,
    val taskStats: ProjectTaskStatsDto,  

    val myRole: String,          

    val createdAt: Instant
) : Identified

data class ProjectTaskStatsDto(
    val total: Int,
    val done: Int,
    val overdue: Int
)

data class ProjectMemberDto(
    val user: UserPublicDto,
    val role: String,            
    val status: String,          
    val joinedAt: Instant? = null
)