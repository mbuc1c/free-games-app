package com.example.freegamesapp.data.repository.game

import android.util.Log
import com.example.freegamesapp.data.api.GameApi
import com.example.freegamesapp.data.entities.toDomain
import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.util.Result

class GameDataSourceRemote(
    val api: GameApi
) : GameDataSource.Remote{
    override suspend fun getGameById(id: Int): Result<GameEntity> = try {
        val result = api.getGameById(id)
        Log.d("MyTag", result.toString())
        Result.Success(result.toDomain())
    } catch (e: Exception) {
        Log.d("MyTag", "Exception: ", e)
        Result.Error(e.localizedMessage as String)
    }
}