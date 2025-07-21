package com.example.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.local.entity.CharacterDao
import com.example.rickandmorty.data.local.entity.CharacterDbo

@Database(entities = [CharacterDbo::class], version = 1)
abstract class CharacterDataBase : RoomDatabase() {
    abstract val characterDao: CharacterDao

    companion object {
        fun create(context: Context): CharacterDataBase {
            return Room.databaseBuilder(
                context = context,
                CharacterDataBase::class.java,
                "CharacterDataBase"
            ).build()
        }
    }


}
