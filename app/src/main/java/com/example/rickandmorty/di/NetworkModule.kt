package com.example.rickandmorty.di

import com.example.rickandmorty.core.BASE_URL
import com.example.rickandmorty.data.remote.service.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}
