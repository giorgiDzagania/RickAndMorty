package com.example.rickandmorty.data.repository

import com.example.rickandmorty.core.ApiCallHelper
import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.core.map
import com.example.rickandmorty.data.remote.mappers.toCharacter
import com.example.rickandmorty.data.remote.mappers.toCharacterList
import com.example.rickandmorty.data.remote.service.ApiService
import com.example.rickandmorty.domain.model.response.CharacterListModel
import com.example.rickandmorty.domain.model.response.CharacterResponseModel
import com.example.rickandmorty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val service: ApiService
) : CharacterRepository {

    override suspend fun getCharacterById(id: Int): OperationStatus<CharacterResponseModel> {
        return ApiCallHelper.safeApiCall {
            service.getCharacterById(id = id)
        }.map { characterDto -> characterDto.toCharacter() }
    }

    override suspend fun getAllCharacters(page: Int): OperationStatus<CharacterListModel> {
        return ApiCallHelper.safeApiCall {
            service.getAllCharacters(page = page)
        }.map { allCharactersDto -> allCharactersDto.toCharacterList() }
    }

    override suspend fun searchCharactersByName(name: String): OperationStatus<CharacterListModel> {
        return ApiCallHelper.safeApiCall {
            service.searchCharactersByName(name = name)
        }.map { charactersListDto -> charactersListDto.toCharacterList() }
    }

}
