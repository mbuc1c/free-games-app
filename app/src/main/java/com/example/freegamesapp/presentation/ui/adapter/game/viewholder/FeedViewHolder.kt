package com.example.freegamesapp.presentation.ui.adapter.game.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freegamesapp.databinding.GameListItemBinding
import com.example.freegamesapp.presentation.entities.GameListItem

class FeedViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root) {

    private val binding = GameListItemBinding.bind(itemView)

    fun bind(game: GameListItem) {
        binding.textView.text = game.title
    }
}