package com.example.pokedex.common.navigator

import androidx.navigation.NavController
import com.example.pokedex.home.HomeFragmentDirections

class HomeNavigator(
    private val navController: NavController
) {

    fun toPokemonDetail(id: Int) {
        navController.navigate(
            HomeFragmentDirections.toPokemonDetail(id)
        )
    }
}