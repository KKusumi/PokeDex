package com.example.pokemon_detail

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.common.delegate.ErrorViewModelDelegate
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.Result
import com.example.pokedex.model.UiState
import com.example.pokedex.domain.GetPokemonDetailUseCase
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
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
                is Ok -> {
                    uiState.postValue(UiState.Loaded)
                    _pokemon.postValue(result.value)
                }
                is Err -> {
                    handleError(result.error)
                }
            }
        }
    }
}