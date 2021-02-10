package com.example.pokedex.model.view

import com.example.response_model.PokemonListResponse

data class PokemonListView(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonListItem>
) {
    companion object {
        fun transform(pokemonListResponse: PokemonListResponse): PokemonListView {
            return PokemonListView(
                count = pokemonListResponse.count,
                next = pokemonListResponse.next ?: "",
                previous = pokemonListResponse.previous ?: "",
                results = pokemonListResponse.results.map { PokemonListItem.transform(it) }
            )
        }
    }

    data class PokemonListItem(
        val name: String,
        val url: String
    ) {
        companion object {
            fun transform(pokemonListResponseResult: PokemonListResponse.Pokemon): PokemonListItem {
                return PokemonListItem(
                    name = pokemonListResponseResult.name,
                    url = pokemonListResponseResult.url
                )
            }
        }

        val number
            get() = generatePokemonNumber(url)

        val imageUrl
            get() = generateImageUrl(number.toString())

        fun getCamelCaseName(): String = if (name.isEmpty()) {
            ""
        } else {
            name[0].toUpperCase() + name.substring(1)
        }

        // PokemonListResponse.Pokemon.urlから図鑑ナンバーを生成する。
        private fun generatePokemonNumber(url: String) =
            url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt()

        private fun generateImageUrl(number: String) = "https://github.com/fanzeyi/pokemon.json/blob/master/images/${number.padStart(3, '0')}.png?raw=true"
    }
}
