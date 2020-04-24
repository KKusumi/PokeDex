package com.example.pokedex.navigator

import androidx.navigation.NavController
import com.example.pokedex.ui.home.HomeFragmentDirections

class HomeNavigator(
    private val navController: NavController
) {

    fun toPokemonDetail(id: Int) {
        navController.navigate(
            HomeFragmentDirections.toPokemonDetail(id)
        )
    }
}