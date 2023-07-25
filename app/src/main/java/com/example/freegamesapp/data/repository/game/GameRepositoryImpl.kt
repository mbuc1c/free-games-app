package com.example.freegamesapp.data.repository.game

import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.repository.GameRepository
import com.example.freegamesapp.domain.util.Result

class GameRepositoryImpl(
    private val remote: GameDataSource.Remote,
    private val local: GameDataSource.Local
) : GameRepository {
    override suspend fun getGames(): Result<List<GameEntity>> {
        sync()
        return local.getGames()
    }

    override suspend fun getGameById(id: Int): Result<GameEntity> = local.getGameById(id)

    @Suppress("UNUSED_EXPRESSION")
    override suspend fun sync(): Boolean {
        when (val result = remote.getGames()) {
            is Result.Success -> {
                local.insertGames(result.data)
                true
            }

            else -> false
        }
        return false
    }

}