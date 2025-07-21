package com.example.rickandmorty.data.remote.mappers

import com.example.rickandmorty.data.remote.dto.response.CharacterDto
import com.example.rickandmorty.data.remote.dto.response.CharactersListDto
import com.example.rickandmorty.data.util.CharacterGender
import com.example.rickandmorty.data.util.CharacterStatus
import com.example.rickandmorty.domain.model.response.CharacterListModel
import com.example.rickandmorty.domain.model.response.CharacterResponseModel

fun CharacterDto.toCharacter(): CharacterResponseModel {
    return CharacterResponseModel(
        id = id,
        name = name,
        status = CharacterStatus.fromString(status),
        species = species,
        type = type,
        gender = CharacterGender.fromString(gender),
        origin = CharacterResponseModel.Origin(name = origin?.name, url = origin?.url),
        location = CharacterResponseModel.Location(name = location?.name, url = location?.url),
        episode = episode,
        imageUrl = image,
        created = created
    )
}

fun CharactersListDto.toCharacterList(): CharacterListModel {
    return CharacterListModel(
        info = info?.let {
            CharacterListModel.Info(
                count = it.count,
                pages = it.pages,
                next = it.next,
                prev = it.prev
            )
        },
        results = results.mapNotNull { it?.toCharacter() }
    )
}
