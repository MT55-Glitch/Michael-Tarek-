package com.englishlearning.app.data.repository

import com.englishlearning.app.data.local.dao.CharacterDao
import com.englishlearning.app.domain.model.Character
import com.englishlearning.app.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : CharacterRepository {

    override suspend fun getAllCharacters(): Result<List<Character>> = try {
        val entities = characterDao.getAllCharacters()
        Result.success(entities.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCharacterById(id: String): Result<Character> = try {
        val entity = characterDao.getCharacterById(id)
        if (entity != null) {
            Result.success(entity.toDomain())
        } else {
            Result.failure(Exception("Character not found"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCharactersByAccent(accent: Character.Accent): Result<List<Character>> = try {
        val entities = characterDao.getCharactersByAccent(accent.name)
        Result.success(entities.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCharactersByPersonality(personality: Character.Personality): Result<List<Character>> = try {
        val entities = characterDao.getCharactersByPersonality(personality.name)
        Result.success(entities.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getFavoriteCharacters(): Result<List<Character>> = try {
        val entities = characterDao.getFavoriteCharacters()
        Result.success(entities.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun setFavoriteCharacter(characterId: String, isFavorite: Boolean): Result<Unit> = try {
        characterDao.setFavoriteCharacter(characterId, isFavorite)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun observeCharacters(): Flow<List<Character>> =
        characterDao.observeAllCharacters().map { entities ->
            entities.map { it.toDomain() }
        }

    private fun com.englishlearning.app.data.local.entity.CharacterEntity.toDomain(): Character =
        Character(
            id = id,
            name = name,
            gender = Character.Gender.valueOf(gender),
            age = Character.Age.valueOf(age),
            personality = Character.Personality.valueOf(personality),
            accent = Character.Accent.valueOf(accent),
            voiceId = voiceId,
            avatarUrl = avatarUrl,
            description = description,
            isDefault = isDefault
        )
}
