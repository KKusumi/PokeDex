package com.example.local.di

import androidx.room.Room
import com.example.local.db.CacheDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            CacheDatabase::class.java,
            "pokedex.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}