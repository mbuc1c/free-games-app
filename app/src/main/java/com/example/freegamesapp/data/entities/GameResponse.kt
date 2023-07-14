package com.example.freegamesapp.data.entities

import com.example.freegamesapp.domain.entities.GameEntity
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("platform") val platform: String,
    @SerializedName("game_url") val gameUrl: String
)

fun GameResponse.toDomain() = GameEntity(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre,
    platform = platform,
    gameUrl = gameUrl
)
