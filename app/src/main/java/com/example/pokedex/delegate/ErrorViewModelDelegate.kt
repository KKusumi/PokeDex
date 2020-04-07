package com.example.pokedex.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.model.PokeDexError
import com.example.pokedex.model.UiState
import com.example.pokedex.util.Event

interface ErrorViewModelDelegate {
    val uiState: MutableLiveData<UiState>
    val showErrorCommand: LiveData<Event<String>>
    fun handleError(exception: Exception)
}

class ErrorViewModelDelegateImpl : ErrorViewModelDelegate {
    override val uiState: MutableLiveData<UiState> = MutableLiveData<UiState>().apply {
        value = UiState.Idle
    }
    override val showErrorCommand: MutableLiveData<Event<String>> = MutableLiveData()

    override fun handleError(exception: Exception) {
        when (exception) {
            is PokeDexError.NetworkError -> {
                uiState.postValue(UiState.Retry)
            }
            is PokeDexError.UndefinedError -> {
                uiState.postValue(UiState.Error)
                showErrorCommand.postValue(Event(exception.alertMessage))
            }
            else -> {
                uiState.postValue(UiState.Error)
            }
        }
    }
}

