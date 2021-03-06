package com.example.pokedex.model.model

sealed class UiState {
    object Idle: UiState()
    object Loading: UiState()
    object Loaded: UiState()
    object Retry: UiState()
    object Error: UiState()
}