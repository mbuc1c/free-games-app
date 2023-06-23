package com.example.freegamesapp.domain.repository

import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.util.Result

interface GameRepository {

    suspend fun getGameById(id: Int): Result<GameEntity>
}