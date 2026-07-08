package com.englishlearning.app.data.repository

import com.englishlearning.app.data.local.dao.ConversationSessionDao
import com.englishlearning.app.data.local.dao.MessageDao
import com.englishlearning.app.domain.model.ConversationSession
import com.englishlearning.app.domain.model.Message
import com.englishlearning.app.domain.repository.ConversationRepository
import com.englishlearning.app.domain.repository.SessionComparison
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val sessionDao: ConversationSessionDao,
    private val messageDao: MessageDao
) : ConversationRepository {

    override suspend fun createSession(session: ConversationSession): Result<String> = try {
        val sessionEntity = session.toEntity()
        val rowId = sessionDao.insertSession(sessionEntity)
        Result.success(session.id)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getSession(sessionId: String): Result<ConversationSession> = try {
        val sessionEntity = sessionDao.getSessionById(sessionId)
        if (sessionEntity != null) {
            val messages = messageDao.getSessionMessages(sessionId)
            val domainSession = sessionEntity.toDomain(
                character = com.englishlearning.app.domain.model.Character(
                    id = sessionEntity.characterId,
                    name = "",
                    gender = com.englishlearning.app.domain.model.Character.Gender.MALE,
                    age = com.englishlearning.app.domain.model.Character.Age.ADULT,
                    personality = com.englishlearning.app.domain.model.Character.Personality.FRIENDLY,
                    accent = com.englishlearning.app.domain.model.Character.Accent.USA,
                    voiceId = "",
                    avatarUrl = "",
                    description = ""
                ),
                messages = messages.map { it.toDomain() }
            )
            Result.success(domainSession)
        } else {
            Result.failure(Exception("Session not found"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getUserSessions(userId: String, limit: Int): Result<List<ConversationSession>> = try {
        val entities = sessionDao.getUserSessions(userId, limit)
        val sessions = entities.map { sessionEntity ->
            val messages = messageDao.getSessionMessages(sessionEntity.id)
            sessionEntity.toDomain(
                character = com.englishlearning.app.domain.model.Character(
                    id = sessionEntity.characterId,
                    name = "",
                    gender = com.englishlearning.app.domain.model.Character.Gender.MALE,
                    age = com.englishlearning.app.domain.model.Character.Age.ADULT,
                    personality = com.englishlearning.app.domain.model.Character.Personality.FRIENDLY,
                    accent = com.englishlearning.app.domain.model.Character.Accent.USA,
                    voiceId = "",
                    avatarUrl = "",
                    description = ""
                ),
                messages = messages.map { it.toDomain() }
            )
        }
        Result.success(sessions)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addMessage(sessionId: String, message: Message): Result<Unit> = try {
        val messageEntity = message.toEntity(sessionId)
        messageDao.insertMessage(messageEntity)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun completeSession(sessionId: String): Result<Unit> = try {
        sessionDao.completeSession(sessionId, System.currentTimeMillis())
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteSession(sessionId: String): Result<Unit> = try {
        val session = sessionDao.getSessionById(sessionId)
        if (session != null) {
            messageDao.deleteSessionMessages(sessionId)
            sessionDao.deleteSession(session)
            Result.success(Unit)
        } else {
            Result.failure(Exception("Session not found"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun observeSessionMessages(sessionId: String): Flow<List<Message>> =
        messageDao.observeSessionMessages(sessionId).map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun compareSessionsBetweenDates(
        userId: String,
        startDate: Long,
        endDate: Long
    ): Result<SessionComparison> = try {
        val sessions = sessionDao.getSessionsBetweenDates(userId, startDate, endDate)
        if (sessions.size >= 2) {
            val session1 = sessions[0]
            val session2 = sessions[1]
            val comparison = SessionComparison(
                session1Id = session1.id,
                session2Id = session2.id,
                scoreImprovement = 0.0, // To be calculated from feedback
                timeGap = session1.startTime - session2.startTime,
                improvementAreas = emptyList(),
                consistentStrengths = emptyList(),
                recommendations = emptyList()
            )
            Result.success(comparison)
        } else {
            Result.failure(Exception("Not enough sessions for comparison"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    private fun ConversationSession.toEntity() =
        com.englishlearning.app.data.local.entity.ConversationSessionEntity(
            id = id,
            userId = userId,
            characterId = characterId,
            topic = topic,
            duration = duration,
            startTime = startTime,
            endTime = endTime,
            isCompleted = isCompleted,
            recordingPath = recordingPath,
            difficulty = difficulty.name
        )

    private fun com.englishlearning.app.data.local.entity.ConversationSessionEntity.toDomain(
        character: com.englishlearning.app.domain.model.Character,
        messages: List<Message> = emptyList()
    ) = ConversationSession(
        id = id,
        userId = userId,
        characterId = characterId,
        character = character,
        topic = topic,
        duration = duration,
        startTime = startTime,
        endTime = endTime,
        isCompleted = isCompleted,
        recordingPath = recordingPath,
        messages = messages,
        difficulty = ConversationSession.Difficulty.valueOf(difficulty)
    )

    private fun Message.toEntity(sessionId: String) =
        com.englishlearning.app.data.local.entity.MessageEntity(
            id = id,
            sessionId = sessionId,
            sender = sender.name,
            text = text,
            audioPath = audioPath,
            timestamp = timestamp,
            duration = duration
        )

    private fun com.englishlearning.app.data.local.entity.MessageEntity.toDomain() = Message(
        id = id,
        sender = Message.MessageSender.valueOf(sender),
        text = text,
        audioPath = audioPath,
        timestamp = timestamp,
        duration = duration
    )
}
