package com.example.freegamesapp.domain.usecase

import com.example.freegamesapp.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllGames @Inject constructor(
    val repository: GameRepository
) {
    suspend fun getGames() = withContext(Dispatchers.IO) {
        repository.getGames()
    }
}
