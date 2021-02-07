package com.example.pokedex.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.common.delegate.ErrorViewModelDelegate
import com.example.pokedex.model.PokemonListResponse
import com.example.pokedex.model.UiState
import com.example.pokedex.domain.GetPokemonListUseCase
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val errorViewModelDelegate: ErrorViewModelDelegate
) : ViewModel(), LifecycleObserver,
    ErrorViewModelDelegate by errorViewModelDelegate {

    // State
    private val _pokemonListResponse: MutableLiveData<PokemonListResponse> = MutableLiveData()
    val pokemonListResponse: LiveData<PokemonListResponse>
        get() = _pokemonListResponse

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            when (val result = getPokemonListUseCase.execute()) {
                is Ok -> {
                    uiState.postValue(UiState.Loaded)
                    _pokemonListResponse.postValue(result.value)
                }
                is Err -> {
                    handleError(result.error)
                }
            }
        }
    }
}