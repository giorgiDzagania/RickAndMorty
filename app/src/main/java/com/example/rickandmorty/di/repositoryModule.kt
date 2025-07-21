package com.example.rickandmorty.di

import com.example.rickandmorty.data.internet_connection.AndroidConnectivityObserver
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import com.example.rickandmorty.data.repository.RoomRepositoryImpl
import com.example.rickandmorty.domain.internet_connection.ConnectivityObserver
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.domain.repository.RoomRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<RoomRepository> { RoomRepositoryImpl(get()) }

    single<ConnectivityObserver> { AndroidConnectivityObserver(androidContext()) }
}
