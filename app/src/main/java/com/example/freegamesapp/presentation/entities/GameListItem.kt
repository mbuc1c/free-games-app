package com.example.freegamesapp.presentation.entities

data class GameListItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String,
    val genre: String,
    val platform: String
)
