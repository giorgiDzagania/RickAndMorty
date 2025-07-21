package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page

sealed interface HomePageEvent {

    data class GoToDetailsScreen(val characterId: Int) : HomePageEvent
    data class LoadPage(val page: Int) : HomePageEvent
}