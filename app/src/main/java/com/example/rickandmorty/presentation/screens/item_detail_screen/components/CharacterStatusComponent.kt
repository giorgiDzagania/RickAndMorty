package com.example.rickandmorty.presentation.screens.item_detail_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.data.util.CharacterStatus
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.ui.theme.RickPrimary

@Composable
fun CharacterStatusComponent(characterStatus: CharacterStatus) {

    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = characterStatus.color,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Status: ${characterStatus.displayName}",
            fontSize = 20.sp,
            color = RickPrimary,
        )
    }
}

@Preview
@Composable
fun CharacterStatusComponentPreviewAlive() {
    RickAndMortyTheme {
        CharacterStatusComponent(characterStatus = CharacterStatus.alive)
    }
}

@Preview
@Composable
fun CharacterStatusComponentPreviewDead() {
    RickAndMortyTheme {
        CharacterStatusComponent(characterStatus = CharacterStatus.dead)
    }
}

@Preview
@Composable
fun CharacterStatusComponentPreviewUnknown() {
    RickAndMortyTheme {
        CharacterStatusComponent(characterStatus = CharacterStatus.unknown)
    }
}