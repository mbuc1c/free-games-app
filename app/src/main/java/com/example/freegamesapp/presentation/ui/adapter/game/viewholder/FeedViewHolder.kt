package com.example.freegamesapp.presentation.ui.adapter.game.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freegamesapp.databinding.GameListItemBinding
import com.example.freegamesapp.presentation.entities.GameListItem

class FeedViewHolder(
    parent: ViewGroup,
    private val action: (gameId: Int) -> Unit
) : RecyclerView.ViewHolder(
    GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
) {

    private val binding = GameListItemBinding.bind(itemView)

    fun bind(game: GameListItem) {
        with(binding) {
            // set title
            textView.text = game.title

            // set image with Glide
            Glide
                .with(root)
                .load(game.thumbnail)
                .into(binding.imageView)

            // set action
            root.setOnClickListener { action(game.id) }
        }
    }
}