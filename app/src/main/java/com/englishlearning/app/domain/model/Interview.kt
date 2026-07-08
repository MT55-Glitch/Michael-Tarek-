package com.englishlearning.app.domain.model

import java.io.Serializable

data class InterviewPrep(
    val id: String,
    val jobTitle: String,
    val jobCategory: JobCategory,
    val level: String,
    val estimatedDuration: Long,
    val modules: List<InterviewModule>,
    val commonQuestions: List<String>,
    val tips: List<String>,
    val vocabularyList: List<VocabularyItem>
) : Serializable {
    enum class JobCategory {
        CALL_CENTER,
        TECHNOLOGY,
        FINANCE,
        HEALTHCARE,
        HOSPITALITY,
        RETAIL,
        EDUCATION,
        OTHER
    }
}

data class InterviewModule(
    val id: String,
    val title: String,
    val description: String,
    val questionIds: List<String>,
    val duration: Long
) : Serializable

data class VocabularyItem(
    val word: String,
    val definition: String,
    val example: String,
    val pronunciation: String,
    val partOfSpeech: String
) : Serializable
