package com.example.freegamesapp.domain.usecase

import android.util.Log
import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.repository.GameRepository
import com.example.freegamesapp.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSelectedGame @Inject constructor(
    val repository: GameRepository
) {
    suspend fun getGame(id: Int): Result<GameEntity> = withContext(Dispatchers.IO) {
        Log.i("MyTag", "getGame()")
        repository.getGameById(id)
    }
}