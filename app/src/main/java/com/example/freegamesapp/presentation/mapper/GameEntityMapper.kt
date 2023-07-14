package com.example.freegamesapp.presentation.mapper

import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.presentation.entities.GameListItem

fun GameEntity.toListItem() = GameListItem(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre,
    platform = platform,
    gameUrl = gameUrl
)
