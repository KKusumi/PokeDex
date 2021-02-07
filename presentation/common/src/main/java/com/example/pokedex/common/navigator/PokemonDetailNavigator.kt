package com.example.pokedex.common.navigator

import androidx.navigation.NavController

class PokemonDetailNavigator(
    private val navController: NavController
) {

    fun toPrev() {
        navController.popBackStack()
    }
}