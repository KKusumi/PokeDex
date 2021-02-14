package com.example.pokedex.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.common.delegate.ErrorViewModelDelegate
import com.example.pokedex.common.ext.combine
import com.example.pokedex.common.ext.toLoadingState
import com.example.pokedex.domain.GetPokemonListUseCase
import com.example.pokedex.model.model.LoadState
import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.view.PokemonListView
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok

class HomeViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val errorViewModelDelegate: ErrorViewModelDelegate
) : ViewModel(), LifecycleObserver,
    ErrorViewModelDelegate by errorViewModelDelegate {

    data class UiModel(
        val isLoading: Boolean,
        var error: PokeDexException?,
        val pokemonListView: PokemonListView
    ) {
        companion object {
            val EMPTY = UiModel(false, null, PokemonListView(results = listOf()))
        }
    }

    private val pokemonListViewLoadState: LiveData<LoadState<PokemonListView>> =
        liveData(context = viewModelScope.coroutineContext) {
            emitSource(
                getPokemonListUseCase.pokemonListView()
                    .toLoadingState()
                    .asLiveData(context = viewModelScope.coroutineContext)
            )

            when (val result = getPokemonListUseCase.refresh()) {
                is Ok -> {
                    // NOP
                }
                is Err -> {
                    uiModel.value?.error = result.error
                }
            }
        }

    val uiModel: MutableLiveData<UiModel> = combine(
        initialValue = UiModel.EMPTY,
        liveData1 = pokemonListViewLoadState
    ) { current, loadState ->
        val pokemonListView = when (loadState) {
            is LoadState.Loaded -> {
                loadState.value
            }
            else -> {
                current.pokemonListView
            }
        }
        UiModel(
            isLoading = loadState.isLoading,
            error = loadState.getErrorIfExists(),
            pokemonListView = pokemonListView
        )
    }
}