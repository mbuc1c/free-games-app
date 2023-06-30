package com.example.freegamesapp.data.repository.game

import android.util.Log
import com.example.freegamesapp.data.api.GameApi
import com.example.freegamesapp.data.entities.toDomain
import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class GameDataSourceRemote(
    val api: GameApi
) : GameDataSource.Remote{
    override suspend fun getGames(): Result<List<GameEntity>> = try {
        val result = api.getGames()
        Result.Success(result.map { it.toDomain() })
    } catch (e: Exception) {
        Log.e("MyTag", "Exception: ", e)
        Result.Error(e.localizedMessage as String)
    }

    override suspend fun getGameById(id: Int): Result<GameEntity> = try {
        val result = api.getGameById(id)
        Log.d("MyTag", result.toString())
        Result.Success(result.toDomain())
    } catch (e: Exception) {
        Log.e("MyTag", "Exception: ", e)
        Result.Error(e.localizedMessage as String)
    }
}