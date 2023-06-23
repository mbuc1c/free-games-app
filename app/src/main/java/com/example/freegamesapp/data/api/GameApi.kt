package com.example.freegamesapp.data.api

import com.example.freegamesapp.data.entities.GameResponse
import com.example.freegamesapp.domain.util.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApi {

    @GET("{id}")
    suspend fun getGameById(
        @Path("id") id: Int
    ) : GameResponse
}