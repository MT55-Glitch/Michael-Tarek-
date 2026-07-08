package com.englishlearning.app.data.local.dao

import androidx.room.*
import com.englishlearning.app.data.local.entity.SessionFeedbackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionFeedbackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeedback(feedback: SessionFeedbackEntity): Long

    @Update
    suspend fun updateFeedback(feedback: SessionFeedbackEntity)

    @Delete
    suspend fun deleteFeedback(feedback: SessionFeedbackEntity)

    @Query("SELECT * FROM session_feedback WHERE sessionId = :sessionId")
    suspend fun getFeedbackBySessionId(sessionId: String): SessionFeedbackEntity?

    @Query("SELECT * FROM session_feedback WHERE sessionId = :sessionId")
    fun observeFeedback(sessionId: String): Flow<SessionFeedbackEntity?>

    @Query("""
        SELECT * FROM session_feedback 
        WHERE sessionId IN (SELECT id FROM conversation_sessions WHERE userId = :userId)
        ORDER BY timestamp DESC
        LIMIT :limit
    """)
    suspend fun getUserFeedback(userId: String, limit: Int = 20): List<SessionFeedbackEntity>

    @Query("""
        SELECT AVG(overallScore) FROM session_feedback
        WHERE sessionId IN (SELECT id FROM conversation_sessions WHERE userId = :userId)
    """)
    suspend fun getUserAverageScore(userId: String): Double?
}
