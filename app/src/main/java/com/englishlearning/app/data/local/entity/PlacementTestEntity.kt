package com.englishlearning.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "placement_tests")
data class PlacementTestEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val startTime: Long,
    val endTime: Long? = null,
    val questionsJson: String, // JSON serialized test questions
    val overallScore: Double? = null,
    val estimatedLevel: String? = null,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
