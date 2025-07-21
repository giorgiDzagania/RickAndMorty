package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.domain.model.response.CharacterListModel
import com.example.rickandmorty.domain.model.response.CharacterResponseModel

interface CharacterRepository {

    suspend fun getCharacterById(id: Int): OperationStatus<CharacterResponseModel>
    suspend fun getAllCharacters(page: Int): OperationStatus<CharacterListModel>
    suspend fun searchCharactersByName(name: String): OperationStatus<CharacterListModel>
}
