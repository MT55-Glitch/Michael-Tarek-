package com.englishlearning.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.englishlearning.app.data.local.dao.*
import com.englishlearning.app.data.local.entity.*

@Database(
    entities = [
        CharacterEntity::class,
        ConversationSessionEntity::class,
        MessageEntity::class,
        SessionFeedbackEntity::class,
        TopicEntity::class,
        PlacementTestEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class EnglishLearningDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun conversationSessionDao(): ConversationSessionDao
    abstract fun messageDao(): MessageDao
    abstract fun sessionFeedbackDao(): SessionFeedbackDao
    abstract fun topicDao(): TopicDao
    abstract fun userDao(): UserDao
}
