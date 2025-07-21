package com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rickandmorty.domain.model.response.CharacterResponseModel
import com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page.components.CharacterItem
import com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page.components.NothingHasSavedYet
import com.example.rickandmorty.ui.theme.RickPrimary
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page.SearchEvent

@Composable
fun SavedItemsScreenDestination(
    onGoBack: () -> Unit,
    onNavigateToDetailsScreen: (Int) -> Unit
) {
    val viewModel = koinViewModel<SavedItemsViewModel>()
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                SavedItemsEffect.GoToBack -> onGoBack()
                is SavedItemsEffect.NavigateToDetails -> onNavigateToDetailsScreen(effect.charId)
            }
        }
    }

    SavedItemsScreen(
        state = state.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SavedItemsScreen(
    modifier: Modifier = Modifier,
    state: SavedItemsState,
    onEvent: (SavedItemsEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RickPrimary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            IconButton(
                onClick = { onEvent(SavedItemsEvent.GoBack) },
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Text(
                text = "Saved Items",
                modifier = Modifier.padding(start = 12.dp),
                color = Color.White,
                fontSize = 23.sp
            )
        }
        if (state.allCharacters.isNotEmpty()) {
            ShowSavedCharacters(
                characters = state.allCharacters,
                onEvent = onEvent,
            )
        } else {
            NothingHasSavedYet()
        }
    }
}

@Composable
fun ShowSavedCharacters(
    characters: List<CharacterResponseModel>,
    onEvent: (SavedItemsEvent) -> Unit,
) {
    LazyColumn {
        items(characters) { character ->
            CharacterItem(character = character, onEvent = onEvent)
        }
    }
}
