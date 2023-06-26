package com.example.freegamesapp.data.api

import com.example.freegamesapp.data.entities.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {

    @GET("/api/game")
    suspend fun getGameById(
        @Query("id") id: Int
    ) : GameResponse
}