package com.englishlearning.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "conversation_sessions",
    foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ConversationSessionEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val characterId: String,
    val topic: String,
    val duration: Long,
    val startTime: Long,
    val endTime: Long? = null,
    val isCompleted: Boolean = false,
    val recordingPath: String? = null,
    val difficulty: String = "INTERMEDIATE",
    val createdAt: Long = System.currentTimeMillis()
)
