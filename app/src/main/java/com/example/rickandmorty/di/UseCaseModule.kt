package com.example.rickandmorty.di

import com.example.rickandmorty.domain.usecases.local.DeleteCharacterUseCase
import com.example.rickandmorty.domain.usecases.local.GetAllSavedCharactersUseCase
import com.example.rickandmorty.domain.usecases.local.SaveCharacterUseCase
import com.example.rickandmorty.domain.usecases.remote.GetAllCharactersUseCase
import com.example.rickandmorty.domain.usecases.remote.GetCharacterByIdUseCase
import com.example.rickandmorty.domain.usecases.remote.SearchCharactersByNameUseCase
import org.koin.dsl.module

val useCaseModule = module {

    /* -------- NetWork ---------- */
    factory { GetCharacterByIdUseCase(get()) }
    factory { GetAllCharactersUseCase(get()) }
    factory { SearchCharactersByNameUseCase(get()) }

    /* -------- Local ---------- */
    factory { SaveCharacterUseCase(get()) }
    factory { GetAllSavedCharactersUseCase(get()) }
    factory { DeleteCharacterUseCase(get()) }
}
