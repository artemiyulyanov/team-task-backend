package com.teamtask.dto

import java.math.BigDecimal
import java.time.Instant


data class TaskDto(
    override val id: String,
    val projectId: String,
    val parentTaskId: String? = null,

    val title: String,
    val description: String? = null,

    val status: String,      
    val priority: String,    

    val assignees: List<UserPublicDto> = emptyList(),
    val tags: List<TagDto> = emptyList(),
    val attachments: List<AttachmentDto> = emptyList(),
    val checklist: List<ChecklistItemDto> = emptyList(),

    val dueDate: Instant? = null,
    val estimatedHours: BigDecimal? = null,

    val subtaskCount: Int = 0,   

    val createdBy: UserPublicDto,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : Identified, Timestamp

data class TagDto(
    override val id: String,
    val name: String,
    val color: String
) : Identified

data class AttachmentDto(
    override val id: String,
    val fileUrl: String,
    val fileName: String,
    val fileSize: Long,
    val mimeType: String,
    val uploadedBy: UserPublicDto,
    val createdAt: Instant
) : Identified

data class ChecklistItemDto(
    override val id: String,
    val text: String,
    val done: Boolean
) : Identified

// облегчённая версия TaskDto для списка agenda items — без checklist/attachments,
// иначе MeetingDto раздувается вложенными задачами с их вложенными задачами
data class TaskSummaryDto(
    val id: String,
    val title: String,
    val status: String
)