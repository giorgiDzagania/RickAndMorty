package com.example.rickandmorty.presentation.btm_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rickandmorty.presentation.navigation.destinations.MainDestinations

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val destination: MainDestinations
)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home, MainDestinations.Home),
    BottomNavItem("Search", Icons.Default.Search, MainDestinations.Search),
    BottomNavItem("Favorites", Icons.Default.Favorite, MainDestinations.Favorites)
)