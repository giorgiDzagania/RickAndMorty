package com.example.rickandmorty.di

import com.example.rickandmorty.presentation.MainViewModel
import com.example.rickandmorty.presentation.screens.item_detail_screen.CharacterViewModel
import com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page.HomePageViewModel
import com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page.SavedItemsViewModel
import com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomePageViewModel(get()) }
    viewModel { CharacterViewModel(get(), get()) }
    viewModel { SavedItemsViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }

    viewModel { MainViewModel(get()) }
}
