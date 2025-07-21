package com.example.rickandmorty.di

import androidx.room.Room
import com.example.rickandmorty.data.local.CharacterDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            CharacterDataBase::class.java,
            "CharacterDataBase"
        ).build()
    }

    single { get<CharacterDataBase>().characterDao }
}
