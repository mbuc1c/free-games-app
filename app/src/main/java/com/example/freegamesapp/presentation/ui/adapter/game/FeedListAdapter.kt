package com.example.freegamesapp.presentation.ui.adapter.game

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.freegamesapp.presentation.entities.GameListItem
import com.example.freegamesapp.presentation.ui.adapter.game.callback.GameDiffCallback
import com.example.freegamesapp.presentation.ui.adapter.game.viewholder.FeedViewHolder
import javax.inject.Inject

class FeedListAdapter @Inject constructor(

) : ListAdapter<GameListItem, FeedViewHolder>(GameDiffCallback())  { // TODO: fix DI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(parent)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}