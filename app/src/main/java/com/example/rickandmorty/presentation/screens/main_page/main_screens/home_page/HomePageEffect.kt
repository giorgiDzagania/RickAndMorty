package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page

sealed class HomePageEffect {

    data class NavigateToDetails(val characterId: Int) : HomePageEffect()
}