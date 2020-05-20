package com.example.pokedex.ui.pokemondetail

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.delegate.ErrorViewModelDelegate
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Result
import com.example.pokedex.model.UiState
import com.example.pokedex.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val errorViewModelDelegate: ErrorViewModelDelegate
) : ViewModel(), LifecycleObserver,
    ErrorViewModelDelegate by errorViewModelDelegate {

    companion object {
        private const val DEFAULT_ID = -1
    }

    // State
    private val _pokemon: MutableLiveData<Pokemon> = MutableLiveData()
    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    private var _id: Int = DEFAULT_ID

    fun retry() {
        if (_id != DEFAULT_ID) {
            fetchData(_id)
        }
    }

    fun fetchData(id: Int) {
        viewModelScope.launch {
            _id = id
            uiState.postValue(UiState.Loading)
            when (val result = getPokemonDetailUseCase.execute(id)) {
                is Result.Success -> {
                    uiState.postValue(UiState.Loaded)
                    _pokemon.postValue(result.data)
                }
                is Result.Error -> {
                    handleError(result.exception)
                }
            }
        }
    }
}