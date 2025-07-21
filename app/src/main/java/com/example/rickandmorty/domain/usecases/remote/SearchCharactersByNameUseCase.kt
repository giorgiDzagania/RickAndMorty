package com.example.rickandmorty.domain.usecases.remote

import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.domain.model.response.CharacterListModel
import com.example.rickandmorty.domain.repository.CharacterRepository

class SearchCharactersByNameUseCase(private val repository: CharacterRepository) {

    suspend operator fun invoke(name: String): OperationStatus<CharacterListModel> {
        return repository.searchCharactersByName(name = name)
    }
}
