package com.example.rickandmorty.domain.usecases.remote

import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.domain.model.response.CharacterResponseModel
import com.example.rickandmorty.domain.repository.CharacterRepository

class GetCharacterByIdUseCase(private val repository: CharacterRepository) {

    suspend operator fun invoke(id: Int): OperationStatus<CharacterResponseModel> {
        return repository.getCharacterById(id = id)
    }
}