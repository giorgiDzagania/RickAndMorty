package com.example.rickandmorty.data.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavorites(character: CharacterDbo)

    @Delete
    suspend fun deleteFromFavorites(character: CharacterDbo)

    @Query("SELECT * FROM characters_table")
    suspend fun getAllFavorites(): List<CharacterDbo>
}
