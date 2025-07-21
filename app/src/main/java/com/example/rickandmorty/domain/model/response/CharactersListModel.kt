package com.example.rickandmorty.domain.model.response

data class CharacterListModel(
    val info: Info?,
    val results: List<CharacterResponseModel>?,
) {

    data class Info(
        val count: Int?,
        val pages: Int?,
        val next: String?,
        val prev: String?
    )
}
