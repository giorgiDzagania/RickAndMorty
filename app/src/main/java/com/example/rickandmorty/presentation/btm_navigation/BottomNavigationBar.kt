package com.example.rickandmorty.presentation.btm_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStack = navController.currentBackStackEntryAsState()

    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = backStack.value?.destination?.hierarchy?.any {
                    it.route == item.destination::class.qualifiedName
                } == true,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) }
            )
        }
    }
}