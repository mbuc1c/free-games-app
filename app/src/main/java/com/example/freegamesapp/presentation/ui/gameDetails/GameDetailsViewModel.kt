package com.example.freegamesapp.presentation.ui.gameDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.usecase.GetSelectedGame
import com.example.freegamesapp.domain.util.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class GameDetailsViewModel @AssistedInject constructor(
    @Assisted private val gameId: Int,
    val getSelectedGame: GetSelectedGame
) : ViewModel() {
    // TODO change to stateFlow to track uiState
    private val _selectedGame: MutableLiveData<GameEntity?> = MutableLiveData()
    val selectedGame: LiveData<GameEntity?> get() = _selectedGame

    @AssistedFactory
    interface Factory {
        fun create(gameId: Int): GameDetailsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun providesFactory(
            assistedFactory: Factory,
            gameId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(gameId) as T
            }
        }
    }
    init {
        getGame(gameId)
    }
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