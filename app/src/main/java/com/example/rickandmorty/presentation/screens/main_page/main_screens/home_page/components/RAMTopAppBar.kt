package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.ui.theme.RickPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RAMTopAppBar(modifier: Modifier = Modifier) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = RickPrimary,
            titleContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RAMTopAppBarPreview() {
    RickAndMortyTheme {
        RAMTopAppBar()
    }
}
