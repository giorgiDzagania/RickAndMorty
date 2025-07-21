package com.example.rickandmorty.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CharactersListDto(
    val info: InfoDto?,
    val results: List<CharacterDto?>,
) {

    @Serializable
    data class InfoDto(
        val count: Int?,
        val pages: Int?,
        val next: String?,
        val prev: String?
    )
}
