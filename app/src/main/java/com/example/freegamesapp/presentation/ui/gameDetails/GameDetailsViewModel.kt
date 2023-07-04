package com.example.freegamesapp.presentation.ui.gameDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.usecase.GetSelectedGame
import com.example.freegamesapp.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    val getSelectedGame: GetSelectedGame
) : ViewModel() {

    private val _selectedGame: MutableLiveData<GameEntity?> = MutableLiveData()
    val selectedGame: LiveData<GameEntity?> get() = _selectedGame

    fun getGame(gameId: Int) {
        viewModelScope.launch {
            val result = getSelectedGame.getGame(gameId)
            when (result) {
                is Result.Success -> _selectedGame.postValue(result.data)
                else -> _selectedGame.postValue(null)
            }
        }
    }
}