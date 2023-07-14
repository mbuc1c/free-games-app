package com.example.freegamesapp.presentation.ui.gameDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.domain.usecase.GetSelectedGame
import com.example.freegamesapp.domain.util.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameDetailsViewModel @AssistedInject constructor(
    @Assisted private val gameId: Int,
    val getSelectedGame: GetSelectedGame
) : ViewModel() {
    private val _uiState: MutableStateFlow<GameDetailsUiState> = MutableStateFlow(
        GameDetailsUiState()
    )
    val uiState: StateFlow<GameDetailsUiState> get() = _uiState

    data class GameDetailsUiState(
        val title: String = "",
        val shortDescription: String = "",
        val thumbnail: String = "",
        val gameUrl: String = ""
    )
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
                is Result.Success -> {
                    with(result.data) {
                        _uiState.tryEmit(
                            GameDetailsUiState(
                                title = title,
                                shortDescription = shortDescription,
                                thumbnail = thumbnail,
                                gameUrl = gameUrl
                            )
                        )
                    }
                }

                else -> return@launch
            }
        }
    }
}