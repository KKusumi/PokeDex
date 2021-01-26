package com.example.pokedex.model

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
) {
    data class Pokemon(
        val name: String,
        val url: String
    ) {
        val number
            get() = generatePokemonNumber(url)

        val frontImageUrl
            get() = generatePokemonFrontImageUrl(number.toString())

        val backImageUrl
            get() = generatePokemonBackImageUrl(number.toString())

        fun getCamelCaseName(): String = if (name.isEmpty()) {
            ""
        } else {
            name[0].toUpperCase() + name.substring(1)
        }

        // PokemonListResponse.Pokemon.urlから図鑑ナンバーを生成する。
        private fun generatePokemonNumber(url: String) =
            url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt()

        private fun generatePokemonFrontImageUrl(number: String) =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

        private fun generatePokemonBackImageUrl(number: String) =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/${number}.png"
    }
}
