package com.example.pokedex.model.model

import com.example.response_model.GameIndiceResponse

data class GameIndice(
    val gameIndex: Int,
    val version: Version
) {
    companion object {
        fun transform(gameIndiceResponse: GameIndiceResponse): GameIndice {
            return GameIndice(
                gameIndex = gameIndiceResponse.game_index,
                version = Version.transform(gameIndiceResponse.version)
            )
        }
    }
}