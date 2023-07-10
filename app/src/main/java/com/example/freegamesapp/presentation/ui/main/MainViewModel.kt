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

) : ViewModel() {

}
