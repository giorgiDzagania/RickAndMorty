package com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page.components.SearchedCharactersList
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.ui.theme.RickPrimary
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreenDestination(
    onNavigateToDetailsScreen: (Int?) -> Unit,
    navigateBack: () -> Unit
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is SearchEffect.NavigateToDetails -> onNavigateToDetailsScreen(effect.charId)
                SearchEffect.NavigateBack -> navigateBack()
            }
        }
    }

    SearchScreen(
        state = state.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    onEvent: (SearchEvent) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = RickPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onEvent(SearchEvent.OnGoBackClick) },
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            OutlinedTextField(
                value = state.searchedText,
                onValueChange = { onEvent(SearchEvent.OnQueryChanged(it)) },
                placeholder = { Text("Search...", color = Color.LightGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    cursorColor = Color.White
                )
            )
        }

        SearchedCharactersList(
            isLoading = state.isLoading,
            characters = state.searchResults,
            onCharacterClick = { character ->
                onEvent(SearchEvent.GoToDetailsScreen(character.id ?: -1))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPagePreview() {
    RickAndMortyTheme {
        SearchScreen(state = SearchState(), onEvent = {})
    }
}