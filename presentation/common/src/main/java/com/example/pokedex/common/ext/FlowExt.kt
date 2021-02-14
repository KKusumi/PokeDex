package com.example.pokedex.common.ext

import com.example.pokedex.model.model.LoadState
import com.example.pokedex.model.model.PokeDexException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.toLoadingState(): Flow<LoadState<T>> {
    return map<T, LoadState<T>> { LoadState.Loaded(it) }
        .onStart {
            emit(LoadState.Loading as LoadState<T>)
        }
        .catch { e ->
            emit(LoadState.Error(PokeDexException(e.cause)))
        }
}