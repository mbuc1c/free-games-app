package com.example.freegamesapp.presentation.ui.adapter.game.callback

import androidx.recyclerview.widget.DiffUtil
import com.example.freegamesapp.presentation.entities.GameListItem

class GameDiffCallback : DiffUtil.ItemCallback<GameListItem>() {
    override fun areItemsTheSame(oldItem: GameListItem, newItem: GameListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GameListItem, newItem: GameListItem): Boolean {
        return oldItem == newItem
    }

}