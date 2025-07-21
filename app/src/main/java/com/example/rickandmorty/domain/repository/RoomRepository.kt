package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.domain.model.response.CharacterResponseModel

interface RoomRepository {

    suspend fun saveCharacter(character: CharacterResponseModel): OperationStatus<Unit>
    suspend fun deleteCharacter(character: CharacterResponseModel): OperationStatus<Unit>
    suspend fun getAllCharacters(): OperationStatus<List<CharacterResponseModel>>
}
