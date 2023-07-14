package com.example.freegamesapp.presentation.ui.gameDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.freegamesapp.databinding.FragmentGameDetailsBinding
import com.example.freegamesapp.presentation.ui.main.MainActivity
import com.example.freegamesapp.presentation.util.FeedDestinationState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GameDetailsFragment : Fragment() {
    private val args: GameDetailsFragmentArgs by navArgs()

    @Inject lateinit var gameDetailsViewModelFactory: GameDetailsViewModel.Factory

    private val viewModel: GameDetailsViewModel by viewModels {
        GameDetailsViewModel.providesFactory(
            assistedFactory = gameDetailsViewModelFactory,
            gameId = args.gameId)
    }

    private var _binding: FragmentGameDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameTitle.isVisible = false
        binding.thumbnail.isVisible = false
        observeViewModel()
        binding.gameTitle.isVisible = true
        binding.thumbnail.isVisible = true
        (requireActivity() as MainActivity).lastFeedDestination = FeedDestinationState.Details(args.gameId)
    }

    private fun observeViewModel() = with(viewModel) {
        viewModelScope.launch {
            uiState.collect {
                handleGameDetailsUiState(it)
            }
        }
    }

    private fun handleGameDetailsUiState(gameState: GameDetailsViewModel.GameDetailsUiState) = with(binding) {
        gameTitle.text = gameState.title
        shortDescription.text = gameState.shortDescription
        loadImage(gameState.thumbnail)
        gameStoreFab.setOnClickListener {
            goToUrl(gameState.gameUrl)
        }
    }

    private fun goToUrl(gameUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(gameUrl)
        startActivity(intent)
    }

    private fun loadImage(thumbnail: String) =
        Glide
            .with(binding.root)
            .load(thumbnail)
            .into(binding.thumbnail)
}