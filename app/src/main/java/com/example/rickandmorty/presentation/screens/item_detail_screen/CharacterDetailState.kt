package com.example.rickandmorty.presentation.screens.item_detail_screen

import com.example.rickandmorty.domain.model.response.CharacterResponseModel

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: CharacterResponseModel? = null,
    val error: String? = null
)
