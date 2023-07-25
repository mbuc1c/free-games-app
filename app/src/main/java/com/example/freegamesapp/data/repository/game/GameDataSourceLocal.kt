package com.example.freegamesapp.data.repository.game

import com.example.freegamesapp.data.db.games.dao.GameDao
import com.example.freegamesapp.data.entities.toDomain
import com.example.freegamesapp.data.mapper.toDbEntity
import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.util.Result

class GameDataSourceLocal(
    private val gameDao: GameDao
) : GameDataSource.Local {

    override suspend fun getGames(): Result<List<GameEntity>> = try {
        val result = gameDao.getGames().map { it.toDomain() }
        Result.Success(result)
    } catch (e: Exception) {
        Result.Error(e.localizedMessage as String)
    }

    override suspend fun getGameById(gameId: Int): Result<GameEntity> = try {
        val result = gameDao.getGameById(gameId).toDomain()
        Result.Success(result)
    } catch (e: Exception) {
        Result.Error(e.localizedMessage as String)
    }

    override suspend fun insertGames(games: List<GameEntity>) =
        gameDao.insertGames(games.map { it.toDbEntity() })
}