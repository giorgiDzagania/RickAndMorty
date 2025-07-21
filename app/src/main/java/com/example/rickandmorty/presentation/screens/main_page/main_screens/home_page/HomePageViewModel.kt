package com.example.rickandmorty.presentation.screens.main_page.main_screens.home_page

import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.core.base.BaseViewModel
import com.example.rickandmorty.domain.usecases.remote.GetAllCharactersUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
) : BaseViewModel<HomePageState, HomePageEvent, HomePageEffect>(
    initialState = HomePageState()
) {

    override fun onEvent(event: HomePageEvent) {
        when (event) {
            is HomePageEvent.GoToDetailsScreen -> onGoToDetailsScreen(event.characterId)
            is HomePageEvent.LoadPage -> loadPage(event.page)
        }
    }

    init {
        loadPage(1)
    }

    private fun onGoToDetailsScreen(characterId: Int) = viewModelScope.launch {
        sendEffect(HomePageEffect.NavigateToDetails(characterId))
    }

    private fun loadPage(page: Int) = viewModelScope.launch {
        updateState { copy(isLoading = true) }
        when (val result = getAllCharactersUseCase.invoke(page)) {
            is OperationStatus.Success -> {
                val character = result.value.results ?: emptyList()
                updateState {
                    copy(
                        allCharacters = character,
                        totalPages = result.value.info?.pages ?: 1,
                        currentPage = page,
                        isLoading = true,
                        errorMessage = null
                    )
                }
                delay(500L)
            }

            is OperationStatus.Failure -> {}
        }
        updateState { copy(isLoading = false) }
    }

}