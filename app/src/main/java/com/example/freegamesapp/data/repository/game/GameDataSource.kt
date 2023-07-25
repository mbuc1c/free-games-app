package com.example.freegamesapp.data.repository.game

import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface GameDataSource {

    interface Remote {
        suspend fun getGames(): Result<List<GameEntity>>
        suspend fun getGameById(id: Int): Result<GameEntity>
    }

    interface Local {
        suspend fun getGames(): Result<List<GameEntity>>
        suspend fun getGameById(gameId: Int): Result<GameEntity>
        suspend fun insertGames(games: List<GameEntity>)
    }
}