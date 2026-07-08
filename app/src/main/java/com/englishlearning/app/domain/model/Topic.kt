package com.englishlearning.app.domain.model

import java.io.Serializable

data class Topic(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val difficulty: String,
    val keywords: List<String>,
    val imageUrl: String? = null,
    val suggestedSentences: List<String>,
    val commonPhrases: List<String>,
    val estimatedDuration: Long,
    val isFavorite: Boolean = false,
    val timesCompleted: Int = 0
) : Serializable
