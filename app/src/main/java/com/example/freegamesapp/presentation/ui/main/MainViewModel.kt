package com.example.freegamesapp.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _selectedGame: MutableLiveData<String> = MutableLiveData()
    val selectedGame: LiveData<String> get()= _selectedGame

    init {
        getSelectedGameTitle()
    }
    private fun getSelectedGameTitle() {
        viewModelScope.launch {
            val gameResult = getSelectedGame.getGame(452)

            if (gameResult is Result.Success) _selectedGame.postValue(gameResult.data.title) else _selectedGame.postValue("error")
        }
    }
}