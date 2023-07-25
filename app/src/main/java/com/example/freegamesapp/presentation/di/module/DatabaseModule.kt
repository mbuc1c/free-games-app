package com.example.freegamesapp.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.example.freegamesapp.data.db.games.GameDatabase
import com.example.freegamesapp.data.db.games.dao.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): GameDatabase {
        return Room.databaseBuilder(
            applicationContext,
            GameDatabase::class.java,
            "games.db"
        ).build()
    }

    @Provides
    fun providesGameDao(gameDatabase: GameDatabase): GameDao {
        return gameDatabase.gameDao()
    }
}