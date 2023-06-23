package com.example.freegamesapp.domain.entities

data class GameEntity(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String,
    val genre: String,
    val platform: String
) {
}