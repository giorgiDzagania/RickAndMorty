package com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page

import com.example.rickandmorty.domain.model.response.CharacterResponseModel

data class SearchState(
    val searchedText: String = "",
    val isSearching: Boolean = false,
    val searchResults: List<CharacterResponseModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)