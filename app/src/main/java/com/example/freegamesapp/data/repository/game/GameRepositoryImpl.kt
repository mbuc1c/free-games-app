package com.example.freegamesapp.data.repository.game

import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.repository.GameRepository
import com.example.freegamesapp.domain.util.Result

class GameRepositoryImpl(
    val remote: GameDataSource.Remote
) : GameRepository {

    override suspend fun getGameById(id: Int): Result<GameEntity> = remote.getGameById(id)
}