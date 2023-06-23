package com.example.freegamesapp.presentation.di.module

import com.example.freegamesapp.data.api.GameApi
import com.example.freegamesapp.data.repository.game.GameDataSource
import com.example.freegamesapp.data.repository.game.GameDataSourceImpl
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
    fun provideGameDataSourceImpl(
        remote: GameDataSource.Remote
    ): GameRepository {
        return GameDataSourceImpl(remote)
    }

    @Provides
    @Singleton
    fun provideGameDataSourceRemote(
        api: GameApi
    ): GameDataSource.Remote {
        return GameDataSourceRemote(api)
    }
}