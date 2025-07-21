package com.example.rickandmorty.data.remote.service

import com.example.rickandmorty.data.remote.dto.response.CharacterDto
import com.example.rickandmorty.data.remote.dto.response.CharactersListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharactersListDto>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<CharacterDto>

    @GET("character")
    suspend fun searchCharactersByName(@Query("name") name: String): Response<CharactersListDto>
}
