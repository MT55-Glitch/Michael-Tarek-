package com.englishlearning.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val gender: String,
    val age: String,
    val personality: String,
    val accent: String,
    val voiceId: String,
    val avatarUrl: String,
    val description: String,
    val isFavorite: Boolean = false,
    val isDefault: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
