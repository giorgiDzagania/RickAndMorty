package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.ui.theme.RickAction

@Composable
fun PaginationBar(
    modifier: Modifier = Modifier,
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit
) {
    val maxPagesToShow = 5
    val startPage = maxOf(1, currentPage - maxPagesToShow / 2)
    val endPage = minOf(totalPages, startPage + maxPagesToShow - 1)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (currentPage > 1) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp))
                    .clickable { onPageSelected(currentPage - 1) }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(text = "<", color = Color.White)
            }
        }

        for (page in startPage..endPage) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .background(
                        if (page == currentPage) RickAction else Color.DarkGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onPageSelected(page) }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = page.toString(),
                    color = if (page == currentPage) Color.Black else Color.White
                )
            }
        }

        if (currentPage < totalPages) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .background(Color.Gray, RoundedCornerShape(8.dp))
                    .clickable { onPageSelected(currentPage + 1) }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(text = ">", color = Color.White)
            }
        }
    }
}
