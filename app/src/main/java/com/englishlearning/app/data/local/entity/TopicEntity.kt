package com.englishlearning.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
data class TopicEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val difficulty: String,
    val keywords: String, // JSON serialized
    val imageUrl: String? = null,
    val suggestedSentences: String, // JSON serialized
    val commonPhrases: String, // JSON serialized
    val estimatedDuration: Long,
    val isFavorite: Boolean = false,
    val timesCompleted: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
