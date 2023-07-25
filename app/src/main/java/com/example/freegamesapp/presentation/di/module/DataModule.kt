package com.example.freegamesapp.presentation.di.module

import android.util.Log
import com.example.freegamesapp.data.api.GameApi
import com.example.freegamesapp.data.db.games.dao.GameDao
import com.example.freegamesapp.data.repository.game.GameDataSource
import com.example.freegamesapp.data.repository.game.GameDataSourceLocal
import com.example.freegamesapp.data.repository.game.GameRepositoryImpl
import com.example.freegamesapp.data.repository.game.GameDataSourceRemote
import com.example.freegamesapp.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGameRepositoryImpl(
        remote: GameDataSource.Remote,
        local: GameDataSource.Local
    ): GameRepository {
        val gameRepositoryImpl = GameRepositoryImpl(remote, local)
        Log.i("MyTag", "Created gameRepositoryImpl")
        return gameRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideGameDataSourceRemote(
        api: GameApi
    ): GameDataSource.Remote {
        val gameDataSourceRemote = GameDataSourceRemote(api)
        Log.i("MyTag", "Created gameDataSourceRemote")
        return gameDataSourceRemote
    }

    @Provides
    @Singleton
    fun provideGameDataSourceLocal(
        gameDao: GameDao
    ): GameDataSource.Local {
        return GameDataSourceLocal(gameDao)
    }
}