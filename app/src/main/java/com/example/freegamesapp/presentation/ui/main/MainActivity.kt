package com.example.freegamesapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.freegamesapp.R
import com.example.freegamesapp.databinding.ActivityMainBinding
import com.example.freegamesapp.presentation.ui.feed.FeedFragment
import com.example.freegamesapp.presentation.ui.feed.FeedFragmentDirections
import com.example.freegamesapp.presentation.util.FeedDestinationState
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    var lastFeedDestination: FeedDestinationState? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        setupViews()

    }

    private fun setupViews() {
        setupBottomNavigationView()

        setSupportActionBar(findViewById(R.id.toolbar)) // Set the ActionBar with setSupportActionBar()
        setupActionBarWithNavController(navController)
    }

    private fun setupBottomNavigationView() {
        with (binding.navigationBottom) {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.feed_item -> {
                        navigateToFeedFragment()
                        true
                    }
                    R.id.settings_item -> {
                        navigateToSettings()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun navigateToFeedFragment() {
        lastFeedDestination?.let { feedDestination ->
            when (feedDestination) {
                is FeedDestinationState.Feed -> {
                    navController.navigate(R.id.feedFragment)
                }
                is FeedDestinationState.Details -> {
//                    navController.navigate(R.id.gameDetailsFragment)
//                    // Pass the gameId as an argument to the details fragment
//                    navController.previousBackStackEntry?.savedStateHandle?.set("gameId", feedDestination.gameId)
                    val bundle = Bundle().apply {
                        putInt("gameId", feedDestination.gameId)
                    }
                    navController.navigate(R.id.gameDetailsFragment, bundle)
                }
            }
        } ?: run {
            // Navigate to the initial feed fragment
            lastFeedDestination = FeedDestinationState.Feed
            navController.navigate(R.id.feedFragment)
        }
    }

    private fun navigateToSettings() {
        navController.navigate(R.id.settingsFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navController.currentDestination?.id == R.id.settingsFragment) {
            navController.popBackStack()
        } else {
            navController.navigateUp()
        }
    }
}