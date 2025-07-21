package com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page

import com.example.rickandmorty.domain.model.response.CharacterResponseModel

data class SavedItemsState(
    val isLoading: Boolean = false,
    val allCharacters: List<CharacterResponseModel> = emptyList(),
    val error: String? = null
)
