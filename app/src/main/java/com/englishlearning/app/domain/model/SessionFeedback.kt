package com.englishlearning.app.domain.model

import java.io.Serializable

data class SessionFeedback(
    val sessionId: String,
    val overallScore: Double,
    val grammarScore: Double,
    val grammarDetails: String,
    val grammarMistakes: List<String>,
    val pronunciationScore: Double,
    val pronunciationDetails: String,
    val ipaNotation: List<IPANotation>,
    val vocabularyScore: Double,
    val vocabularyDetails: String,
    val suggestedVocabulary: List<String>,
    val fluencyScore: Double,
    val fluencyDetails: String,
    val tonalityScore: Double,
    val tonalityDetails: String,
    val clarityScore: Double,
    val clarityDetails: String,
    val strengthPoints: List<String>,
    val painPoints: List<String>,
    val suggestions: List<String>,
    val benchmarkLevel: BenchmarkLevel,
    val timestamp: Long
) : Serializable {
    enum class BenchmarkLevel {
        BEGINNER, ELEMENTARY, INTERMEDIATE, UPPER_INTERMEDIATE, ADVANCED, PROFICIENCY
    }
}

data class IPANotation(
    val word: String,
    val userPronunciation: String,
    val correctPronunciation: String,
    val accuracy: Double,
    val suggestion: String
) : Serializable
