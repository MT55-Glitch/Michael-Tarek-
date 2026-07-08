package com.englishlearning.app.domain.repository

import com.englishlearning.app.domain.model.User
import com.englishlearning.app.domain.model.UserProgress
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getCurrentUser(): Result<User>
    suspend fun updateUser(user: User): Result<Unit>
    suspend fun updateUserProgress(sessionDuration: Long, score: Double): Result<Unit>
    suspend fun getUserProgress(): Result<UserProgress>
    suspend fun updateEnglishLevel(level: String): Result<Unit>
    suspend fun updatePreferredAccent(accent: String): Result<Unit>
    suspend fun updateTheme(theme: String): Result<Unit>
    suspend fun updateSaveDataMode(enabled: Boolean): Result<Unit>
    suspend fun increaseStreak(): Result<Unit>
    suspend fun resetStreak(): Result<Unit>
    fun observeUser(): Flow<User>
}
