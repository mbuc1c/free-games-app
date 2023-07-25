package com.example.freegamesapp.data.mapper

import com.example.freegamesapp.data.entities.GameDb
import com.example.freegamesapp.domain.entities.GameEntity

fun GameEntity.toDbEntity() = GameDb(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre,
    platform = platform,
    gameUrl = gameUrl
)