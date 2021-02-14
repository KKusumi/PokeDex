package com.example.repository

import com.example.pokedex.model.model.NetworkException
import com.example.pokedex.model.model.PokeDexApiException
import com.example.pokedex.model.model.PokeDexException
import com.example.pokedex.model.model.UndefinedException
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ApiRepository {
    suspend fun <T> execute(block: suspend () -> Result<T, PokeDexApiException>): Result<T?, PokeDexException> {
        return try {
            block.invoke()
        } catch (e: SocketTimeoutException) {
            Err(NetworkException(e))
        } catch (e: UnknownHostException) {
            Err(NetworkException(e))
        } catch (e: Exception) {
            Err(UndefinedException(e))
        }
    }
}