package com.example.rickandmorty.data.remote.mappers

import com.example.rickandmorty.data.local.entity.CharacterDbo
import com.example.rickandmorty.data.util.CharacterGender
import com.example.rickandmorty.data.util.CharacterStatus
import com.example.rickandmorty.domain.model.response.CharacterResponseModel

fun CharacterResponseModel.toCharacterDbo(): CharacterDbo {
    return CharacterDbo(
        id = this.id ?: 0,
        title = this.name.orEmpty(),
        image = this.imageUrl.orEmpty()
    )
}

fun CharacterDbo.toCharacterResponseModel(): CharacterResponseModel {
    return CharacterResponseModel(
        id = this.id,
        name = this.title,
        imageUrl = this.image,
        status = CharacterStatus.unknown,
        species = null,
        type = null,
        gender = CharacterGender.unknown,
        origin = null,
        location = null,
        episode = emptyList(),
        created = null
    )
}
