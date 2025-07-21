package com.example.rickandmorty.data.util

import androidx.compose.ui.graphics.Color

enum class CharacterStatus(val displayName: String, val color: Color) {
    alive(displayName = "Alive", Color.Green),
    dead(displayName = "Dead", Color.Red),
    unknown(displayName = "Unknown", Color.Yellow);

    companion object {
        fun fromString(value: String?): CharacterStatus {
            return when (value?.lowercase()) {
                "alive" -> alive
                "dead" -> dead
                else -> unknown
            }
        }
    }
}

enum class CharacterGender {
    male,
    female,
    genderless,
    unknown;

    companion object {
        fun fromString(value: String?): CharacterGender {
            return when (value?.lowercase()) {
                "male" -> male
                "female" -> female
                "Genderless" -> genderless
                else -> unknown
            }
        }
    }
}