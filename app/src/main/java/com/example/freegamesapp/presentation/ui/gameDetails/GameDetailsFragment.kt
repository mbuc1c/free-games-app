package com.example.freegamesapp.presentation.ui.gameDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.freegamesapp.R
import com.example.freegamesapp.databinding.FragmentFeedBinding
import com.example.freegamesapp.databinding.FragmentGameDetailsBinding
import com.example.freegamesapp.presentation.ui.main.MainActivity
import com.example.freegamesapp.presentation.util.FeedDestinationState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailsFragment : Fragment() {

    private val viewModel: GameDetailsViewModel by viewModels()

    private val args: GameDetailsFragmentArgs by navArgs()

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
        val gameId = args.gameId
        viewModel.getGame(gameId)

        viewModel.selectedGame.observe(viewLifecycleOwner) {
            binding.gameTitle.text = it?.title ?: "Couldn't get selected game!"
            binding.gameTitle.isVisible = true
        }
        (requireActivity() as MainActivity).lastFeedDestination = FeedDestinationState.Details(args.gameId)
    }
}