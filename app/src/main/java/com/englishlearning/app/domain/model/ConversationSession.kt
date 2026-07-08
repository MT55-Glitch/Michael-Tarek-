package com.englishlearning.app.domain.model

import java.io.Serializable

data class ConversationSession(
    val id: String,
    val userId: String,
    val characterId: String,
    val character: Character,
    val topic: String,
    val duration: Long,
    val startTime: Long,
    val endTime: Long? = null,
    val isCompleted: Boolean = false,
    val recordingPath: String? = null,
    val messages: List<Message> = emptyList(),
    val feedback: SessionFeedback? = null,
    val difficulty: Difficulty = Difficulty.INTERMEDIATE
) : Serializable {
    enum class Difficulty {
        BEGINNER, INTERMEDIATE, ADVANCED, PROFESSIONAL
    }
}

data class Message(
    val id: String,
    val sender: MessageSender,
    val text: String,
    val audioPath: String? = null,
    val timestamp: Long,
    val duration: Long? = null
) : Serializable {
    enum class MessageSender {
        USER, AI
    }
}
