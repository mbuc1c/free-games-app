package com.example.freegamesapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.freegamesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.gameName.text = viewModel.selectedGame
    }
}