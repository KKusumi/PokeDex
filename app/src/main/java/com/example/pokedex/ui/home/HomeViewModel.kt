package com.example.pokedex.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.PokemonListResponse
import com.example.pokedex.delegate.ErrorViewModelDelegate
import com.example.pokedex.model.Result
import com.example.pokedex.model.UiState
import com.example.pokedex.domain.GetPokemonListUseCase
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

    fun fetchData() {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            when (val result = getPokemonListUseCase.execute()) {
                is Result.Success -> {
                    uiState.postValue(UiState.Loaded)
                    _pokemonListResponse.postValue(result.data)
                }
                is Result.Error -> {
                    handleError(result.exception)
                }
            }
        }
    }
}