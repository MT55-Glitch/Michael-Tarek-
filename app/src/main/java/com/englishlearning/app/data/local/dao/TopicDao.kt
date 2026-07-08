package com.englishlearning.app.data.local.dao

import androidx.room.*
import com.englishlearning.app.data.local.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: TopicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(topics: List<TopicEntity>)

    @Update
    suspend fun updateTopic(topic: TopicEntity)

    @Delete
    suspend fun deleteTopic(topic: TopicEntity)

    @Query("SELECT * FROM topics WHERE id = :id")
    suspend fun getTopicById(id: String): TopicEntity?

    @Query("SELECT * FROM topics")
    suspend fun getAllTopics(): List<TopicEntity>

    @Query("SELECT * FROM topics")
    fun observeAllTopics(): Flow<List<TopicEntity>>

    @Query("SELECT * FROM topics WHERE category = :category")
    suspend fun getTopicsByCategory(category: String): List<TopicEntity>

    @Query("SELECT * FROM topics WHERE difficulty = :difficulty")
    suspend fun getTopicsByDifficulty(difficulty: String): List<TopicEntity>

    @Query("SELECT * FROM topics WHERE isFavorite = 1")
    suspend fun getFavoriteTopics(): List<TopicEntity>

    @Query("UPDATE topics SET isFavorite = :isFavorite WHERE id = :topicId")
    suspend fun setFavoriteTopic(topicId: String, isFavorite: Boolean)

    @Query("UPDATE topics SET timesCompleted = timesCompleted + 1 WHERE id = :topicId")
    suspend fun incrementCompletionCount(topicId: String)
}
