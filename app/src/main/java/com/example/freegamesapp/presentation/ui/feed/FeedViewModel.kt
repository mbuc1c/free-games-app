package com.example.freegamesapp.presentation.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freegamesapp.domain.usecase.GetAllGames
import com.example.freegamesapp.domain.util.Result
import com.example.freegamesapp.presentation.entities.GameListItem
import com.example.freegamesapp.presentation.mapper.toListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    val getAllGames: GetAllGames
) : ViewModel() {

    private val _allGames: MutableLiveData<List<GameListItem>?> = MutableLiveData()
    val allGames: LiveData<List<GameListItem>?> get()= _allGames

    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch {
            val result = getAllGames.getGames()
            when (result) {
                is Result.Success -> _allGames.postValue(result.data.map { it.toListItem() })
                else -> _allGames.postValue(null)
            }
        }
    }
}