package com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page

sealed class SearchEffect {

    data class NavigateToDetails(val charId: Int?) : SearchEffect()
    data object NavigateBack: SearchEffect()
}