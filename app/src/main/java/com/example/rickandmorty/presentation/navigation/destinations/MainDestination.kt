package com.example.rickandmorty.presentation.navigation.destinations

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainDestinations {

    @Serializable
    object Home: MainDestinations

    @Serializable
    object Search: MainDestinations

    @Serializable
    object Favorites: MainDestinations

    @Serializable
    data class Details(val id: Int): MainDestinations
}
