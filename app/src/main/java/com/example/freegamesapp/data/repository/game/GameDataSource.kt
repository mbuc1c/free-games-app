package com.example.freegamesapp.data.repository.game

import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.util.Result

interface GameDataSource {

    interface Remote {
        suspend fun getGameById(id: Int): Result<GameEntity>
    }
}