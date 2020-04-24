package com.example.pokedex.util.ext

// PokemonListResponse.Pokemon.urlから図鑑ナンバーを生成する。
fun generatePokemonNumber(url: String) =
    url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt()

fun generatePokemonFrontImageUrl(number: String) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

fun generatePokemonBackImageUrl(number: String) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/${number}.png"