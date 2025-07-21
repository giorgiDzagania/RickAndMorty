package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun ItemCharacter(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(120.dp)
            .clickable {
                onClick.invoke()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                model = image,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_placeholder),
            )
            Spacer(modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = title
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ItemCharacterPreview() {
    RickAndMortyTheme {
        ItemCharacter(
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            title = "Rick Sanchez",
            onClick = {}
        )
    }
}
