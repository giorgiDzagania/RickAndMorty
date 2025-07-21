package com.example.rickandmorty.presentation.screens.main_page.main_screens.saved_page

import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.core.base.BaseViewModel
import com.example.rickandmorty.domain.model.response.CharacterResponseModel
import com.example.rickandmorty.domain.usecases.local.DeleteCharacterUseCase
import com.example.rickandmorty.domain.usecases.local.GetAllSavedCharactersUseCase
import kotlinx.coroutines.launch

class SavedItemsViewModel(
    val getAllSavedCharactersUseCase: GetAllSavedCharactersUseCase,
    val deleteCharacterUseCase: DeleteCharacterUseCase,
) : BaseViewModel<SavedItemsState, SavedItemsEvent, SavedItemsEffect>(
    initialState = SavedItemsState()
) {

    override fun onEvent(event: SavedItemsEvent) {
        when (event) {
            is SavedItemsEvent.GoToDetailsScreen -> onGoToDetailsScreen(event.characterId)
            is SavedItemsEvent.DeleteCharacter -> onDeleteCharacter(event.character)
            is SavedItemsEvent.GoBack -> onGoBackClick()
        }
    }

    init {
        getAllSavedCharacters()
    }

    private fun onGoBackClick() = viewModelScope.launch {
        sendEffect(SavedItemsEffect.GoToBack)
    }

    private fun onGoToDetailsScreen(characterId: Int?) = viewModelScope.launch {
        sendEffect(SavedItemsEffect.NavigateToDetails(charId = characterId ?: -1))
    }

    private fun onDeleteCharacter(character: CharacterResponseModel) = viewModelScope.launch {
        updateState { copy(isLoading = true) }
        when (deleteCharacterUseCase.invoke(character)) {
            is OperationStatus.Success -> {
                updateState { copy(isLoading = false) }
                getAllSavedCharacters()
            }

            is OperationStatus.Failure -> {
                updateState { copy(isLoading = false) }
            }
        }
        updateState { copy(isLoading = false) }
    }

    private fun getAllSavedCharacters() = viewModelScope.launch {
        updateState { copy(isLoading = true) }
        when (val result = getAllSavedCharactersUseCase.invoke()) {
            is OperationStatus.Success -> {
                updateState {
                    copy(
                        isLoading = false,
                        allCharacters = result.value,
                        error = null
                    )
                }
            }

            is OperationStatus.Failure -> {
                updateState {
                    copy(
                        isLoading = false,
                    )
                }
            }
        }
        updateState { copy(isLoading = false) }
    }
}