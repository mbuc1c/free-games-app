package com.example.freegamesapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.freegamesapp.R
import com.example.freegamesapp.databinding.ActivityMainBinding
import com.example.freegamesapp.presentation.util.FeedDestinationState
import dagger.hilt.android.AndroidEntryPoint

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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        setupViews()

    }

    private fun setupViews() {
        setupBottomNavigationView()

        setSupportActionBar(findViewById(R.id.toolbar)) // Set the ActionBar with setSupportActionBar()
        setupActionBarWithNavController(navController)
    }

    private fun setupBottomNavigationView() {
        val navigationBottom = binding.navigationBottom

        navigationBottom.setOnItemSelectedListener { item ->
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

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.feedFragment -> selectBottomNavigationItem(R.id.feed_item)
                R.id.gameDetailsFragment -> selectBottomNavigationItem(R.id.feed_item)
                R.id.settingsFragment -> selectBottomNavigationItem(R.id.settings_item)
            }
        }
    }
    private fun selectBottomNavigationItem(itemId: Int) {
        val bottomNavigationView = binding.navigationBottom
        val menu = bottomNavigationView.menu
        val item = menu.findItem(itemId)
        item?.isChecked = true
    }

    private fun navigateToFeedFragment() {
        lastFeedDestination?.let { feedDestination ->
            when (feedDestination) {
                is FeedDestinationState.Feed -> {
                    navController.navigate(R.id.feedFragment)
                }
                is FeedDestinationState.Details -> {
                    if (navController.currentDestination?.id == R.id.gameDetailsFragment) {
                        navController.navigate(R.id.feedFragment)
                    } else {
                        val bundle = Bundle().apply {
                            putInt("gameId", feedDestination.gameId)
                        }
                        navController.navigate(R.id.gameDetailsFragment, bundle)
                    }
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