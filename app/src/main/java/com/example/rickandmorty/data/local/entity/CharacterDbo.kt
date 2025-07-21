package com.example.rickandmorty.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharacterDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String,
    val title: String,
)
