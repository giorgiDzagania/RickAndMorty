package com.example.rickandmorty.presentation.screens.main_page.main_screens.search_page

import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.core.base.BaseViewModel
import com.example.rickandmorty.domain.usecases.remote.SearchCharactersByNameUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchCharactersByNameUseCase: SearchCharactersByNameUseCase
) : BaseViewModel<SearchState, SearchEvent, SearchEffect>(
    initialState = SearchState()
) {
    private var searchJob: Job? = null

    override fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChanged -> onQueryChanged(event.query)
            is SearchEvent.GoToDetailsScreen -> onGoToDetailsScreen(event.characterId)
            SearchEvent.OnGoBackClick -> onGoBackClick()
        }
    }

    private fun onGoBackClick() = viewModelScope.launch {
        sendEffect(SearchEffect.NavigateBack)
    }

    private fun onGoToDetailsScreen(characterId: Int?) = viewModelScope.launch {
        sendEffect(SearchEffect.NavigateToDetails(charId = characterId))
    }

    private fun onQueryChanged(query: String) {
        updateState { copy(searchedText = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (query.isBlank()) {
                updateState { copy(searchResults = emptyList(), isLoading = false, error = null) }
                return@launch
            }

            updateState { copy(isLoading = true, error = null) }
            delay(500L)
            when (val result = searchCharactersByNameUseCase(query)) {
                is OperationStatus.Success -> updateState {
                    copy(searchResults = result.value.results ?: emptyList(), isLoading = false)
                }

                is OperationStatus.Failure -> updateState {
                    copy(
                        searchResults = emptyList(),
                        isLoading = false,
                        error = result.exception?.message
                    )
                }
            }
        }
    }

}
