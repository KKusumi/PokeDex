package com.example.local.di

import androidx.room.Room
import com.example.local.db.CacheDatabase
import com.example.local.db.PokemonListItemDatabase
import com.example.local.db.RoomDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            CacheDatabase::class.java,
            "pokedex.db"
        ).fallbackToDestructiveMigration().build() as CacheDatabase
    }

    single {
        RoomDatabase(
            cacheDatabase = get()
        ) as PokemonListItemDatabase
    }
}