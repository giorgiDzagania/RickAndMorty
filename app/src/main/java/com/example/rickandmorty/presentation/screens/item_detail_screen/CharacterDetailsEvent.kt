package com.example.rickandmorty.presentation.screens.item_detail_screen

sealed interface CharacterDetailsEvent {

    data object OnSaveItemClick : CharacterDetailsEvent
    data object OnBackPressed : CharacterDetailsEvent
}
