package com.englishlearning.app.domain.model

import java.io.Serializable

data class PlacementTest(
    val id: String,
    val userId: String,
    val startTime: Long,
    val endTime: Long? = null,
    val sections: List<TestSection>,
    val overallScore: Double? = null,
    val estimatedLevel: String? = null,
    val isCompleted: Boolean = false
) : Serializable

data class TestSection(
    val id: String,
    val name: String,
    val questions: List<TestQuestion>,
    val score: Double? = null
) : Serializable

data class TestQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val userAnswer: String? = null,
    val isCorrect: Boolean? = null
) : Serializable
