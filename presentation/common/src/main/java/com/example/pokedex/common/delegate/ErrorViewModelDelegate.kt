package com.example.pokedex.common.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.model.model.NetworkException
import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.model.UndefinedException
import com.example.pokedex.model.model.UiState
import com.example.pokedex.common.util.Event

interface ErrorViewModelDelegate {
    val uiState: MutableLiveData<UiState>
    val showErrorCommand: LiveData<Event<String>>
    fun handleError(e: PokeDexException)
}

class ErrorViewModelDelegateImpl : ErrorViewModelDelegate {
    override val uiState: MutableLiveData<UiState> = MutableLiveData<UiState>().apply {
        value = UiState.Idle
    }
    override val showErrorCommand: MutableLiveData<Event<String>> = MutableLiveData()

    override fun handleError(e: PokeDexException) {
        when (e) {
            is NetworkException -> {
                uiState.postValue(UiState.Retry)
            }
            is UndefinedException -> {
                uiState.postValue(UiState.Error)
            }
            else -> {
                uiState.postValue(UiState.Error)
            }
        }
    }
}

