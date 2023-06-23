package com.example.freegamesapp.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.domain.usecase.GetSelectedGame
import com.example.freegamesapp.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * class for testing retrofit
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    val getSelectedGame: GetSelectedGame
) : ViewModel() {

    val selectedGame = getSelectedGameTitle()

    private fun getSelectedGameTitle(): String? {
        var title: String? = null
        viewModelScope.launch {
            val game = getSelectedGame.getGame(452)

            if (game is Result.Success) title = game.data.title else title = "Error in viewmodel"
        }
        return title
    }
}