package com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmorty.data.util.CharacterGender
import com.example.rickandmorty.data.util.CharacterStatus
import com.example.rickandmorty.domain.model.response.CharacterResponseModel
import com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page.SavedItemsEvent
import com.example.rickandmorty.ui.theme.RickAction
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: CharacterResponseModel,
    onEvent: (SavedItemsEvent) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(MaterialTheme.shapes.medium)
            .border(2.dp, color = RickAction, MaterialTheme.shapes.medium)
            .background(Color.DarkGray)
            .clickable {
                onEvent(SavedItemsEvent.GoToDetailsScreen(character.id))
            },
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(MaterialTheme.shapes.medium),
            model = character.imageUrl,
            contentDescription = character.name,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(1.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF4CAF50))
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = character.name ?: "Unknown",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        showDialog = true
                    }
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Delete Character") },
            text = { Text("Are you sure you want to delete ${character.name}?") },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(SavedItemsEvent.DeleteCharacter(character))
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("No")
                }
            }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun CharacterItemPreview() {
    RickAndMortyTheme {
        CharacterItem(
            character = CharacterResponseModel(
                id = 1,
                name = "Rick Sanchez",
                status = CharacterStatus.unknown,
                species = "Human",
                type = "",
                gender = CharacterGender.unknown,
                origin = CharacterResponseModel.Origin("Earth", ""),
                location = CharacterResponseModel.Location("Citadel of Ricks", ""),
                episode = emptyList(),
                imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                created = ""
            ),
            onEvent = {}
        )
    }
}
