package com.englishlearning.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session_feedback")
data class SessionFeedbackEntity(
    @PrimaryKey
    val sessionId: String,
    val overallScore: Double,
    val grammarScore: Double,
    val grammarDetails: String,
    val grammarMistakes: String, // JSON serialized
    val pronunciationScore: Double,
    val pronunciationDetails: String,
    val ipaNotations: String, // JSON serialized
    val vocabularyScore: Double,
    val vocabularyDetails: String,
    val suggestedVocabulary: String, // JSON serialized
    val fluencyScore: Double,
    val fluencyDetails: String,
    val tonalityScore: Double,
    val tonalityDetails: String,
    val clarityScore: Double,
    val clarityDetails: String,
    val strengthPoints: String, // JSON serialized
    val painPoints: String, // JSON serialized
    val suggestions: String, // JSON serialized
    val benchmarkLevel: String,
    val timestamp: Long,
    val createdAt: Long = System.currentTimeMillis()
)
