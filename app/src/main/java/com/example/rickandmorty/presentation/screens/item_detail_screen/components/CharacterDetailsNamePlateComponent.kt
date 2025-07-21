package com.example.rickandmorty.presentation.screens.item_detail_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.data.util.CharacterStatus
import com.example.rickandmorty.ui.theme.RickAction

@Composable
fun CharacterDetailsNamePlateComponent(
    name: String, status: CharacterStatus
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CharacterStatusComponent(characterStatus = status)
        Text(
            text = name,
            fontSize = 42.sp,
            lineHeight = 42.sp,
            fontWeight = FontWeight.Bold,
            color = RickAction
        )
    }

}

@Preview
@Composable
fun NamePlatePreviewAlive() {
    CharacterDetailsNamePlateComponent(name = "Rick Sanchez", status = CharacterStatus.alive)
}

@Preview
@Composable
fun NamePlatePreviewDead() {
    CharacterDetailsNamePlateComponent(name = "Rick Sanchez", status = CharacterStatus.dead)
}

@Preview
@Composable
fun NamePlatePreviewUnknown() {
    CharacterDetailsNamePlateComponent(name = "Rick Sanchez", status = CharacterStatus.unknown)
}
