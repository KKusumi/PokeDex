package com.example.pokedex.util.ext



fun String.convertToCamelCase() = if (this.isEmpty()) {
    ""
} else {
    this[0].toUpperCase() + this.substring(1)
}