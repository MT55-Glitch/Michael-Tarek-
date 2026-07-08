package com.englishlearning.app.domain.repository

import com.englishlearning.app.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getAllCharacters(): Result<List<Character>>
    suspend fun getCharacterById(id: String): Result<Character>
    suspend fun getCharactersByAccent(accent: Character.Accent): Result<List<Character>>
    suspend fun getCharactersByPersonality(personality: Character.Personality): Result<List<Character>>
    suspend fun getFavoriteCharacters(): Result<List<Character>>
    suspend fun setFavoriteCharacter(characterId: String, isFavorite: Boolean): Result<Unit>
    fun observeCharacters(): Flow<List<Character>>
}
