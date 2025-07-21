package com.example.rickandmorty.presentation.navigation.nav_host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.rickandmorty.presentation.navigation.animations.defaultEnterTransition
import com.example.rickandmorty.presentation.navigation.animations.defaultExitTransition
import com.example.rickandmorty.presentation.navigation.animations.defaultPopEnterTransition
import com.example.rickandmorty.presentation.navigation.animations.defaultPopExitTransition
import com.example.rickandmorty.presentation.navigation.destinations.MainDestinations
import com.example.rickandmorty.presentation.screens.item_detail_screen.CharacterDetailsDestination
import com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.HomeDestination
import com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page.SavedItemsScreenDestination
import com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page.SearchScreenDestination

@Composable
fun MainNavHost(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navHostController,
        startDestination = MainDestinations.Home,
        enterTransition = { defaultEnterTransition() },
        exitTransition = { defaultExitTransition() },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() },
        modifier = modifier
    ) {

        composable<MainDestinations.Home> {
            HomeDestination(
                onGoToDetailsScreen = { characterId: Int ->
                    navHostController.navigate(MainDestinations.Details(id = characterId))
                }
            )
        }

        composable<MainDestinations.Search> {
            SearchScreenDestination(
                onNavigateToDetailsScreen = { characterId: Int ->
                    navHostController.navigate(MainDestinations.Details(id = characterId))
                } as (Int?) -> Unit,
                navigateBack = { navHostController.popBackStack() }
            )
        }

        composable<MainDestinations.Favorites> {
            SavedItemsScreenDestination(
                onGoBack = { navHostController.popBackStack() },
                onNavigateToDetailsScreen = { characterId: Int ->
                    navHostController.navigate(MainDestinations.Details(id = characterId))
                }
            )
        }

        composable<MainDestinations.Details> {
            val characterId = it.toRoute<MainDestinations.Details>()
            CharacterDetailsDestination(
                characterId = characterId.id,
                onGoBackClick = { navHostController.popBackStack() }
            )
        }
    }

}
