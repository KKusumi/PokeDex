package com.example.pokedex.model.model

interface ErrorGettable {
    fun getErrorIfExists(): PokeDexException?
}

sealed class LoadState<out T> : ErrorGettable {
    object Loading : LoadState<Nothing>()
    class Loaded<T>(val value: T) : LoadState<T>()
    class Error<T>(val e: PokeDexException) : LoadState<T>()

    val isLoading get() = this is Loading
    override fun getErrorIfExists() = if (this is Error) e else null
    fun getValueOrNull(): T? = if (this is Loaded<T>) value else null
}