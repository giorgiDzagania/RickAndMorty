package com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page

sealed interface SearchEvent {

    data class OnQueryChanged(val query: String) : SearchEvent
    data class GoToDetailsScreen(var characterId: Int): SearchEvent
    data object OnGoBackClick: SearchEvent
}
