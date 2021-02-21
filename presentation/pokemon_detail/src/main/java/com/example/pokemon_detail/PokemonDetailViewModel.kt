package com.example.pokemon_detail

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.pokedex.common.delegate.ErrorViewModelDelegate
import com.example.pokedex.common.ext.combine
import com.example.pokedex.common.ext.toLoadingState
import com.example.pokedex.domain.GetPokemonDetailUseCase
import com.example.pokedex.model.model.LoadState
import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.view.PokemonDetailView
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.Dispatchers

class PokemonDetailViewModel(
    private val id: Int,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val errorViewModelDelegate: ErrorViewModelDelegate
) : ViewModel(), LifecycleObserver,
    ErrorViewModelDelegate by errorViewModelDelegate {

    data class UiModel(
        val isLoading: Boolean,
        var error: PokeDexException?,
        val pokemonDetailView: PokemonDetailView
    ) {
        companion object {
            val EMPTY = UiModel(false, null, PokemonDetailView.Empty)
        }
    }

    private val pokemonDetailViewLoadState: LiveData<LoadState<PokemonDetailView>> = liveData {
        if (getPokemonDetailUseCase.isSaved(id)) {
            emitSource(
                getPokemonDetailUseCase.queryData(id)
                    .toLoadingState()
                    .asLiveData(Dispatchers.IO)
            )
        }
        when (val result = getPokemonDetailUseCase.fetchData(id)) {
            is Ok -> {
                emitSource(
                    MutableLiveData(LoadState.Loaded(result.value))
                )
            }
            is Err -> {
                // Handle Error
            }
        }
    }

    val uiModel: MutableLiveData<UiModel> = combine(
        initialValue = UiModel.EMPTY,
        liveData1 = pokemonDetailViewLoadState
    ) { current, loadState ->
        val pokemonDetailView = when (loadState) {
            is LoadState.Loaded -> {
                loadState.value
            }
            else -> {
                current.pokemonDetailView
            }
        }
        UiModel(
            isLoading = loadState.isLoading,
            error = loadState.getErrorIfExists(),
            pokemonDetailView = pokemonDetailView
        )
    }
}