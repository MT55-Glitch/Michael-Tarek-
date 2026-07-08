package com.englishlearning.app.domain.model

import java.io.Serializable

data class User(
    val id: String,
    val email: String,
    val name: String,
    val profileImageUrl: String? = null,
    val englishLevel: String = "BEGINNER",
    val preferredAccent: Character.Accent = Character.Accent.USA,
    val theme: AppTheme = AppTheme.SYSTEM,
    val saveDataMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val totalLearningHours: Double = 0.0,
    val streakDays: Int = 0,
    val joinDate: Long,
    val lastActiveDate: Long
) : Serializable {
    enum class AppTheme {
        LIGHT, DARK, SYSTEM
    }
}

data class UserProgress(
    val userId: String,
    val totalSessions: Int,
    val totalDuration: Long,
    val averageScore: Double,
    val improvements: List<Improvement>,
    val weeklyStats: Map<String, Double>,
    val monthlyStats: Map<String, Double>
) : Serializable

data class Improvement(
    val category: String,
    val previousScore: Double,
    val currentScore: Double,
    val percentageChange: Double,
    val date: Long
) : Serializable
