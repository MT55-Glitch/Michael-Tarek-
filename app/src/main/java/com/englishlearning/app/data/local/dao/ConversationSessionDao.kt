package com.englishlearning.app.data.local.dao

import androidx.room.*
import com.englishlearning.app.data.local.entity.ConversationSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: ConversationSessionEntity): Long

    @Update
    suspend fun updateSession(session: ConversationSessionEntity)

    @Delete
    suspend fun deleteSession(session: ConversationSessionEntity)

    @Query("SELECT * FROM conversation_sessions WHERE id = :sessionId")
    suspend fun getSessionById(sessionId: String): ConversationSessionEntity?

    @Query("SELECT * FROM conversation_sessions WHERE userId = :userId ORDER BY startTime DESC LIMIT :limit")
    suspend fun getUserSessions(userId: String, limit: Int = 50): List<ConversationSessionEntity>

    @Query("SELECT * FROM conversation_sessions WHERE userId = :userId ORDER BY startTime DESC")
    fun observeUserSessions(userId: String): Flow<List<ConversationSessionEntity>>

    @Query("SELECT * FROM conversation_sessions WHERE isCompleted = 1 AND userId = :userId ORDER BY startTime DESC LIMIT :limit")
    suspend fun getCompletedSessions(userId: String, limit: Int = 50): List<ConversationSessionEntity>

    @Query("SELECT * FROM conversation_sessions WHERE userId = :userId AND startTime BETWEEN :startTime AND :endTime ORDER BY startTime DESC")
    suspend fun getSessionsBetweenDates(userId: String, startTime: Long, endTime: Long): List<ConversationSessionEntity>

    @Query("UPDATE conversation_sessions SET isCompleted = 1, endTime = :endTime WHERE id = :sessionId")
    suspend fun completeSession(sessionId: String, endTime: Long)

    @Query("SELECT COUNT(*) FROM conversation_sessions WHERE userId = :userId")
    suspend fun getSessionCount(userId: String): Int

    @Query("SELECT SUM(duration) FROM conversation_sessions WHERE userId = :userId")
    suspend fun getTotalLearningDuration(userId: String): Long?
}
