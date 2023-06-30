package com.example.freegamesapp.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.domain.entities.GameEntity
import com.example.freegamesapp.domain.usecase.GetAllGames
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
    val getSelectedGame: GetSelectedGame,
    val getAllGames: GetAllGames
) : ViewModel() {
    private val _allGames: MutableLiveData<List<GameEntity>?> = MutableLiveData()
    val allGames: MutableLiveData<List<GameEntity>?> get()= _allGames

    private val _selectedGame: MutableLiveData<String> = MutableLiveData()
    val selectedGame: LiveData<String> get()= _selectedGame

    init {
        getGames()
//        getSelectedGameTitle()
    }

    private fun getGames() {
        viewModelScope.launch {
            val games = getAllGames.getGames()

            if (games is Result.Success) _allGames.postValue(games.data) else _allGames.postValue(null)
        }
    }

    private fun getSelectedGameTitle() {
        viewModelScope.launch {
            val gameResult = getSelectedGame.getGame(452)

            if (gameResult is Result.Success) _selectedGame.postValue(gameResult.data.title) else _selectedGame.postValue("error")
        }
    }
}