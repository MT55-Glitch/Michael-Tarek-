package com.englishlearning.app.domain.model

import java.io.Serializable

data class Character(
    val id: String,
    val name: String,
    val gender: Gender,
    val age: Age,
    val personality: Personality,
    val accent: Accent,
    val voiceId: String,
    val avatarUrl: String,
    val description: String,
    val isDefault: Boolean = false
) : Serializable {
    enum class Gender {
        MALE, FEMALE, NON_BINARY
    }

    enum class Age {
        YOUTH, YOUNG_ADULT, ADULT, SENIOR
    }

    enum class Personality {
        FORMAL, INFORMAL, FRIENDLY, PROFESSIONAL, CASUAL, SLANG
    }

    enum class Accent {
        USA, UK
    }
}
