package com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page

sealed class SavedItemsEffect {

    data class NavigateToDetails(val charId: Int) : SavedItemsEffect()
    data object GoToBack : SavedItemsEffect()
}
