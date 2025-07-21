package com.example.rickandmorty.presentation.screens.item_detail_screen


sealed class CharacterDetailsEffect {

    data object GoToBackButton: CharacterDetailsEffect()
}
