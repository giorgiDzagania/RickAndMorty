package com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.ui.theme.RickPrimary

@Composable
fun NothingHasSavedYet(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = RickPrimary)
            .padding(16.dp)
    ) {
        Text(
            text = "Nothing saved yet!\nStart exploring and save your favorite characters.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Color.White,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NothingSavedTextPreview() {
    RickAndMortyTheme {
        NothingHasSavedYet()
    }
}
