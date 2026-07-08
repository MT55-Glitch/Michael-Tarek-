package com.englishlearning.app.domain.repository

import com.englishlearning.app.domain.model.Topic
import kotlinx.coroutines.flow.Flow

interface TopicRepository {
    suspend fun getAllTopics(): Result<List<Topic>>
    suspend fun getTopicsByCategory(category: String): Result<List<Topic>>
    suspend fun getTopicsByDifficulty(difficulty: String): Result<List<Topic>>
    suspend fun getTopicById(id: String): Result<Topic>
    suspend fun setFavoriteTopic(topicId: String, isFavorite: Boolean): Result<Unit>
    suspend fun incrementTopicCompletionCount(topicId: String): Result<Unit>
    fun observeTopics(): Flow<List<Topic>>
}
