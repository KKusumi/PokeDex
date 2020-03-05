package com.example.pokedex.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.usecase.GetPokemonDetailUseCase

class HomeViewModel(
    private val GetAllPokemonUseCase: GetPokemonDetailUseCase
) : ViewModel(), LifecycleObserver {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}