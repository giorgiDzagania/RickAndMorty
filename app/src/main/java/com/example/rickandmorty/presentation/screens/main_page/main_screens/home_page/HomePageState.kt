package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page

import com.example.rickandmorty.domain.model.response.CharacterResponseModel

data class HomePageState(
    val isLoading: Boolean = false,
    val allCharacters: List<CharacterResponseModel> = emptyList(),
    val currentPage: Int = 1,
    val totalPages: Int = 42,
    val errorMessage: String? = null
)
