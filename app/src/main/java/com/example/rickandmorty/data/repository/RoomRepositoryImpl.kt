package com.example.rickandmorty.data.repository

import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.core.RoomCallHelper
import com.example.rickandmorty.data.local.entity.CharacterDao
import com.example.rickandmorty.data.remote.mappers.toCharacterDbo
import com.example.rickandmorty.data.remote.mappers.toCharacterResponseModel
import com.example.rickandmorty.domain.model.response.CharacterResponseModel
import com.example.rickandmorty.domain.repository.RoomRepository

class RoomRepositoryImpl(
    private val dao: CharacterDao
) : RoomRepository {

    override suspend fun saveCharacter(character: CharacterResponseModel): OperationStatus<Unit> {
        return RoomCallHelper.safeRoomCall {
            dao.saveToFavorites(character = character.toCharacterDbo())
        }
    }

    override suspend fun deleteCharacter(character: CharacterResponseModel): OperationStatus<Unit> {
        return RoomCallHelper.safeRoomCall {
            dao.deleteFromFavorites(character = character.toCharacterDbo())
        }
    }

    override suspend fun getAllCharacters(): OperationStatus<List<CharacterResponseModel>> {
        return RoomCallHelper.safeRoomCall {
            dao.getAllFavorites().map { it.toCharacterResponseModel() }
        }
    }
}
