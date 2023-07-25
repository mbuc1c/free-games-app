package com.example.freegamesapp.domain.repository

import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.util.Result

interface GameRepository {
    suspend fun getGames(): Result<List<GameEntity>>
    suspend fun getGameById(id: Int): Result<GameEntity>
    suspend fun sync(): Boolean
}