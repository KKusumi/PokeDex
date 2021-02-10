package com.example.pokedex.api.response

open class PokeDexException

open class PokeDexApiException: PokeDexException()

class PokeDexApiResponseException: PokeDexApiException()

class EmptyResponseBodyException : PokeDexException()

class NetworkException(t: Throwable): PokeDexException()

class UndefinedException(t: Throwable) : PokeDexException()
