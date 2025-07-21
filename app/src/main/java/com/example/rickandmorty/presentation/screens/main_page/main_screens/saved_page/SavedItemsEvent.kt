package com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page

import com.example.rickandmorty.domain.model.response.CharacterResponseModel

interface SavedItemsEvent {

    data class GoToDetailsScreen(val characterId: Int?) : SavedItemsEvent
    data class DeleteCharacter(val character: CharacterResponseModel) : SavedItemsEvent
    object GoBack : SavedItemsEvent
}
