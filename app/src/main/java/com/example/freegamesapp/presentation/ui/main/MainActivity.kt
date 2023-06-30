package com.example.freegamesapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.freegamesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.allGames.observe(this) {
//            binding.gameName.text = viewModel.allGames.value.toString()
            Log.d("MyTag", viewModel.allGames.value.toString())
        }
    }
}