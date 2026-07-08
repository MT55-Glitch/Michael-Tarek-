package com.englishlearning.app.data.local.dao

import androidx.room.*
import com.englishlearning.app.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Update
    suspend fun updateCharacter(character: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: String): CharacterEntity?

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters")
    fun observeAllCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE accent = :accent")
    suspend fun getCharactersByAccent(accent: String): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE personality = :personality")
    suspend fun getCharactersByPersonality(personality: String): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    suspend fun getFavoriteCharacters(): List<CharacterEntity>

    @Query("UPDATE characters SET isFavorite = :isFavorite WHERE id = :characterId")
    suspend fun setFavoriteCharacter(characterId: String, isFavorite: Boolean)

    @Query("SELECT * FROM characters WHERE isDefault = 1 LIMIT 1")
    suspend fun getDefaultCharacter(): CharacterEntity?
}
