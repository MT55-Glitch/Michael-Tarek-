package com.englishlearning.app.domain.repository

import com.englishlearning.app.domain.model.SessionFeedback

interface FeedbackRepository {
    suspend fun saveFeedback(feedback: SessionFeedback): Result<Unit>
    suspend fun getFeedback(sessionId: String): Result<SessionFeedback>
    suspend fun generateFeedback(sessionId: String): Result<SessionFeedback>
    suspend fun getHistoricalFeedback(userId: String, limit: Int = 20): Result<List<SessionFeedback>>
}
