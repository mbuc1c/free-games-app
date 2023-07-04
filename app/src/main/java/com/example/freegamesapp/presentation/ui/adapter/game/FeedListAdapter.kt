package com.example.freegamesapp.presentation.ui.adapter.game

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.freegamesapp.presentation.entities.GameListItem
import com.example.freegamesapp.presentation.ui.adapter.game.callback.GameDiffCallback
import com.example.freegamesapp.presentation.ui.adapter.game.viewholder.FeedViewHolder

class FeedListAdapter(
    private val action: (gameId: Int) -> Unit
) : ListAdapter<GameListItem, FeedViewHolder>(GameDiffCallback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(parent, action)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}