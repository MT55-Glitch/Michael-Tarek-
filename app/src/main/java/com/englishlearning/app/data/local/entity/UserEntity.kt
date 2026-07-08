package com.englishlearning.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val email: String,
    val name: String,
    val profileImageUrl: String? = null,
    val englishLevel: String = "BEGINNER",
    val preferredAccent: String = "USA",
    val theme: String = "SYSTEM",
    val saveDataMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val totalLearningHours: Double = 0.0,
    val streakDays: Int = 0,
    val joinDate: Long,
    val lastActiveDate: Long,
    val createdAt: Long = System.currentTimeMillis()
)
