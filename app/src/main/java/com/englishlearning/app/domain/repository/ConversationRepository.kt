package com.englishlearning.app.domain.repository

import com.englishlearning.app.domain.model.ConversationSession
import com.englishlearning.app.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {
    suspend fun createSession(session: ConversationSession): Result<String>
    suspend fun getSession(sessionId: String): Result<ConversationSession>
    suspend fun getUserSessions(userId: String, limit: Int = 50): Result<List<ConversationSession>>
    suspend fun addMessage(sessionId: String, message: Message): Result<Unit>
    suspend fun completeSession(sessionId: String): Result<Unit>
    suspend fun deleteSession(sessionId: String): Result<Unit>
    fun observeSessionMessages(sessionId: String): Flow<List<Message>>
    suspend fun compareSessionsBetweenDates(userId: String, startDate: Long, endDate: Long): Result<SessionComparison>
}

data class SessionComparison(
    val session1Id: String,
    val session2Id: String,
    val scoreImprovement: Double,
    val timeGap: Long,
    val improvementAreas: List<String>,
    val consistentStrengths: List<String>,
    val recommendations: List<String>
)
