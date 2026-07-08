package com.englishlearning.app.domain.usecase

import com.englishlearning.app.domain.model.ConversationSession
import com.englishlearning.app.domain.repository.ConversationRepository
import com.englishlearning.app.domain.repository.UserRepository
import javax.inject.Inject

class CreateConversationUseCase @Inject constructor(
    private val conversationRepository: ConversationRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        characterId: String,
        topic: String,
        difficulty: ConversationSession.Difficulty
    ): Result<String> = try {
        val user = userRepository.getCurrentUser().getOrThrow()
        val session = ConversationSession(
            id = java.util.UUID.randomUUID().toString(),
            userId = user.id,
            characterId = characterId,
            character = com.englishlearning.app.domain.model.Character(
                id = characterId,
                name = "",
                gender = com.englishlearning.app.domain.model.Character.Gender.MALE,
                age = com.englishlearning.app.domain.model.Character.Age.ADULT,
                personality = com.englishlearning.app.domain.model.Character.Personality.FRIENDLY,
                accent = com.englishlearning.app.domain.model.Character.Accent.USA,
                voiceId = "",
                avatarUrl = "",
                description = ""
            ),
            topic = topic,
            duration = 0L,
            startTime = System.currentTimeMillis(),
            difficulty = difficulty
        )
        conversationRepository.createSession(session)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
