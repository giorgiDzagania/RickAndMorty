package com.example.rickandmorty.presentation.screens.item_detail_screen

import android.util.Log.d
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.OperationStatus
import com.example.rickandmorty.core.base.BaseViewModel
import com.example.rickandmorty.domain.usecases.local.SaveCharacterUseCase
import com.example.rickandmorty.domain.usecases.remote.GetCharacterByIdUseCase
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val saveCharacterUseCase: SaveCharacterUseCase
) : BaseViewModel<CharacterDetailState, CharacterDetailsEvent, CharacterDetailsEffect>(
    initialState = CharacterDetailState()
) {

    override fun onEvent(event: CharacterDetailsEvent) {
        when (event) {
            is CharacterDetailsEvent.OnSaveItemClick -> onSaveItemClick()
            CharacterDetailsEvent.OnBackPressed -> onBackPressedClick()
        }
    }

    private fun onSaveItemClick() = viewModelScope.launch {
        val characterToSave = viewState.value.character
        if (characterToSave != null) {
            when (saveCharacterUseCase.invoke(characterToSave)) {
                is OperationStatus.Success -> {}
                is OperationStatus.Failure -> {}
            }
        } else {
            d("CharacterViewModel", "No character available to save")
        }
    }

    internal fun getCharacterById(id: Int) = viewModelScope.launch {
        updateState { copy(isLoading = true) }
        when (val result = getCharacterByIdUseCase(id)) {
            is OperationStatus.Failure -> {}
            is OperationStatus.Success -> {
                val character = result.value
                updateState {
                    copy(
                        isLoading = false,
                        character = character
                    )
                }
            }

        }
        updateState { copy(isLoading = false) }
    }

    private fun onBackPressedClick() = viewModelScope.launch {
        sendEffect(CharacterDetailsEffect.GoToBackButton)
    }

}