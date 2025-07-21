package com.example.rickandmorty.domain.model.response

import com.example.rickandmorty.data.util.CharacterGender
import com.example.rickandmorty.data.util.CharacterStatus

data class CharacterResponseModel(
    val id: Int?,
    val name: String?,
    val status: CharacterStatus,
    val species: String?,
    val type: String?,
    val gender: CharacterGender,
    val origin: Origin?,
    val location: Location?,
    val episode: List<String?>,
    val imageUrl: String?,
    val created: String?
) {

    data class Origin(
        val name: String?,
        val url: String?
    )

    data class Location(
        val name: String?,
        val url: String?
    )
}
