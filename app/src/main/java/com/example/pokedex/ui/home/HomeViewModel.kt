package com.example.pokedex.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.response.PokemonListResponse
import com.example.pokedex.model.PokeDexError
import com.example.pokedex.model.Result
import com.example.pokedex.model.UiState
import com.example.pokedex.usecase.GetPokemonListUseCase
import com.example.pokedex.util.Event
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel(), LifecycleObserver {

    // State
    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState>
        get() = _uiState
    private val _pokemonListResponse: MutableLiveData<PokemonListResponse> = MutableLiveData()
    val pokemonListResponse: LiveData<PokemonListResponse>
        get() = _pokemonListResponse

    // Event
    private val _showErrorCommand: MutableLiveData<Event<String>> = MutableLiveData()
    val showErrorCommand: LiveData<Event<String>>
        get() = _showErrorCommand

    fun fetchData() {
        viewModelScope.launch {
            _uiState.postValue(UiState.Loading)
            when (val result = getPokemonListUseCase.execute()) {
                is Result.Success -> {
                    _uiState.postValue(UiState.Loaded)
                    _pokemonListResponse.postValue(result.data)
                }
                is Result.Error -> {
                    when (result.exception) {
                        is PokeDexError.NetworkError -> {
                            _uiState.postValue(UiState.Retry)
                        }
                        is PokeDexError.UndefinedError -> {
                            _uiState.postValue(UiState.Error)
                            result.exception.message?.let { message ->
                                _showErrorCommand.postValue(Event(message))
                            }
                        }
                    }
                }
            }
        }
    }
}