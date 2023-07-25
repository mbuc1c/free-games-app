package com.example.freegamesapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.freegamesapp.domain.entities.GameEntity

@Entity(tableName = "games")
data class GameDb (
    @PrimaryKey val id: Int,
    val title: String,
    val thumbnail: String,
    @ColumnInfo(name = "short_description") val shortDescription: String,
    val genre: String,
    val platform: String,
    @ColumnInfo(name = "game_url") val gameUrl: String,
)

fun GameDb.toDomain() = GameEntity(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre,
    platform = platform,
    gameUrl = gameUrl
)
