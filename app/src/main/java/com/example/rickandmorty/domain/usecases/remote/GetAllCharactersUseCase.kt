package com.example.rickandmorty.domain.usecases.remote

import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.domain.model.response.CharacterListModel
import com.example.rickandmorty.domain.repository.CharacterRepository

class GetAllCharactersUseCase(private val repository: CharacterRepository) {

    suspend operator fun invoke(page: Int): OperationStatus<CharacterListModel> {
        return repository.getAllCharacters(page = page)
    }
}