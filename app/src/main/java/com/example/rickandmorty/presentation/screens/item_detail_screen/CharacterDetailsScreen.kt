package com.example.rickandmorty.presentation.screens.item_detail_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmorty.ui.theme.RickAction
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.ui.theme.RickPrimary
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailsDestination(
    characterId: Int,
    onGoBackClick: () -> Unit
) {

    val viewModel = koinViewModel<CharacterViewModel>()
    val state = viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(characterId) {
        viewModel.getCharacterById(characterId)
        viewModel.effects.collect { effect ->
            when (effect) {
                CharacterDetailsEffect.GoToBackButton -> onGoBackClick()
            }
        }
    }

    CharacterDetailsScreen(
        state = state.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    state: CharacterDetailState,
    onEvent: (CharacterDetailsEvent) -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    val fabColor by animateColorAsState(
        targetValue = if (clicked) Color.Red else MaterialTheme.colorScheme.onPrimary
    )

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        state.character?.let { character ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(RickPrimary)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onEvent(CharacterDetailsEvent.OnBackPressed) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = character.name ?: "Unknown",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = RickAction,
                        modifier = Modifier.weight(1f)
                    )

                    FloatingActionButton(
                        modifier = Modifier.size(48.dp),
                        onClick = {
                            clicked = true
                            onEvent(CharacterDetailsEvent.OnSaveItemClick)
                        },
                        containerColor = fabColor
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Heart"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = rememberAsyncImagePainter(character.imageUrl),
                    contentDescription = "${character.name} image",
                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                CharacterDetailText(label = "Status", value = character.status.displayName)
                CharacterDetailText(label = "Species", value = character.species ?: "Unknown")
                CharacterDetailText(label = "Gender", value = character.gender.name)
                CharacterDetailText(label = "Origin", value = character.origin?.name ?: "Unknown")
                CharacterDetailText(
                    label = "Location",
                    value = character.location?.name ?: "Unknown"
                )
                CharacterDetailText(label = "Episodes", value = character.episode.size.toString())

                Spacer(modifier = Modifier.height(32.dp))


            }

        }
    }

}

@Composable
fun CharacterDetailText(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
            color = RickAction,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontSize = 18.sp
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
fun ItemDetailsScreenPreview() {
    RickAndMortyTheme {
        CharacterDetailsScreen(
            state = CharacterDetailState(),
            onEvent = {}
        )
    }
}