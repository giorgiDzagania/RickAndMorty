package com.example.rickandmorty.presentation.navigation.animations

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun defaultEnterTransition() = slideInHorizontally { it }

fun defaultExitTransition() = slideOutHorizontally { -it / 2 } + fadeOut(targetAlpha = 0.65f)

fun defaultPopEnterTransition() = slideInHorizontally { -it / 2 } + fadeIn(initialAlpha = 0.65f)

fun defaultPopExitTransition() = slideOutHorizontally { it }
