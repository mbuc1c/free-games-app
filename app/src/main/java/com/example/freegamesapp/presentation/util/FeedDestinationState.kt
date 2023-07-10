package com.example.freegamesapp.presentation.util

sealed class FeedDestinationState {
    object Feed : FeedDestinationState()
    data class Details(val gameId: Int) : FeedDestinationState()
}
