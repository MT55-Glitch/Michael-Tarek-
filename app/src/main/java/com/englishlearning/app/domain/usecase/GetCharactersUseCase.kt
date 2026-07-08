package com.englishlearning.app.domain.usecase

import com.englishlearning.app.domain.model.Character
import com.englishlearning.app.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend fun getAllCharacters() = characterRepository.getAllCharacters()
    
    suspend fun getCharactersByAccent(accent: Character.Accent) = 
        characterRepository.getCharactersByAccent(accent)
    
    suspend fun getCharactersByPersonality(personality: Character.Personality) = 
        characterRepository.getCharactersByPersonality(personality)
    
    suspend fun setFavorite(characterId: String, isFavorite: Boolean) = 
        characterRepository.setFavoriteCharacter(characterId, isFavorite)
}
