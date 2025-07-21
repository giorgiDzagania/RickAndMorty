package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.components.ItemCharacter
import com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.components.RAMTopAppBar
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.components.PaginationBar
import com.example.rickandmorty.ui.theme.RickPrimary

@Composable
fun HomeDestination(
    onGoToDetailsScreen: (Int) -> Unit
) {
    val viewModel = koinViewModel<HomePageViewModel>()
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is HomePageEffect.NavigateToDetails -> {
                    onGoToDetailsScreen(effect.characterId)
                }
            }
        }
    }

    HomePageScreen(
        state = state.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HomePageScreen(
    modifier: Modifier = Modifier,
    state: HomePageState,
    onEvent: (HomePageEvent) -> Unit,
) {

    Column(modifier = modifier.background(RickPrimary)) {
        RAMTopAppBar()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(vertical = 1.dp)
        )
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier =  Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.allCharacters) { character ->
                    ItemCharacter(
                        image = character.imageUrl ?: "",
                        title = character.name ?: "Unknown",
                        onClick = { onEvent(HomePageEvent.GoToDetailsScreen(character.id ?: -1)) }
                    )
                }
            }

            PaginationBar(
                currentPage = state.currentPage,
                totalPages = state.totalPages,
                onPageSelected = { page -> onEvent(HomePageEvent.LoadPage(page)) }
            )
        }

    }
}
