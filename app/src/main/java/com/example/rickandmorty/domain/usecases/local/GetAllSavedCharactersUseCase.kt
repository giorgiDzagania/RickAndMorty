package com.example.rickandmorty.domain.usecases.local

import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.domain.model.response.CharacterResponseModel
import com.example.rickandmorty.domain.repository.RoomRepository

class GetAllSavedCharactersUseCase(private val repository: RoomRepository) {

    suspend operator fun invoke(): OperationStatus<List<CharacterResponseModel>> {
        return repository.getAllCharacters()
    }
}
