package com.example.pokedex.di

import com.example.pokedex.delegate.ErrorViewModelDelegate
import com.example.pokedex.delegate.ErrorViewModelDelegateImpl
import org.koin.dsl.module

val delegateModule = module {
    factory {
        ErrorViewModelDelegateImpl() as ErrorViewModelDelegate
    }
}