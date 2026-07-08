package com.englishlearning.app.data.local.dao

import androidx.room.*
import com.englishlearning.app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getCurrentUser(): UserEntity?

    @Query("SELECT * FROM users LIMIT 1")
    fun observeCurrentUser(): Flow<UserEntity?>

    @Query("UPDATE users SET englishLevel = :level WHERE id = :userId")
    suspend fun updateEnglishLevel(userId: String, level: String)

    @Query("UPDATE users SET preferredAccent = :accent WHERE id = :userId")
    suspend fun updatePreferredAccent(userId: String, accent: String)

    @Query("UPDATE users SET theme = :theme WHERE id = :userId")
    suspend fun updateTheme(userId: String, theme: String)

    @Query("UPDATE users SET saveDataMode = :enabled WHERE id = :userId")
    suspend fun updateSaveDataMode(userId: String, enabled: Boolean)

    @Query("UPDATE users SET totalLearningHours = totalLearningHours + :hours WHERE id = :userId")
    suspend fun addLearningHours(userId: String, hours: Double)

    @Query("UPDATE users SET streakDays = streakDays + 1 WHERE id = :userId")
    suspend fun increaseStreak(userId: String)

    @Query("UPDATE users SET streakDays = 0 WHERE id = :userId")
    suspend fun resetStreak(userId: String)

    @Query("UPDATE users SET lastActiveDate = :timestamp WHERE id = :userId")
    suspend fun updateLastActiveDate(userId: String, timestamp: Long)
}
