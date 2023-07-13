package com.example.freegamesapp.presentation.ui.gameDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.freegamesapp.databinding.FragmentGameDetailsBinding
import com.example.freegamesapp.presentation.ui.main.MainActivity
import com.example.freegamesapp.presentation.util.FeedDestinationState
import dagger.hilt.android.AndroidEntryPoint
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
        viewModel.selectedGame.observe(viewLifecycleOwner) {
            binding.gameTitle.text = it?.title ?: "Couldn't get selected game!"
            Glide
                .with(binding.root)
                .load(it?.thumbnail)
                .into(binding.thumbnail)
            binding.shortDescription.text = it?.shortDescription
            binding.gameTitle.isVisible = true
            binding.thumbnail.isVisible = true
        }
        (requireActivity() as MainActivity).lastFeedDestination = FeedDestinationState.Details(args.gameId)
    }
}