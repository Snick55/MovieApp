package com.android.movieapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.transition.TransitionInflater
import com.android.movieapp.MainActivity
import com.android.movieapp.R
import com.android.movieapp.databinding.FragmentDetailsBinding
import com.android.movieapp.presentation.MoviesUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var isMovieFavorited: Boolean? = null
    private lateinit var movie: MoviesUi
    private val viewModel by viewModels<DetailsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        (requireActivity() as MainActivity).changeTitle(getString(R.string.details_fragment))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        movie = requireArguments().getSerializable(MOVIE) as MoviesUi
        movie.showImage(binding.imageView)

        movie.showDetails(
            binding.titleTextview, binding.description,
            binding.dateTextview, binding.rattingTextview,
            R.string.ratting_of_film, R.string.year_of_film
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            isMovieFavorited = viewModel.isMovieFavorite(movie)
            updateButtonIcon()
        }

        binding.favorite.setOnClickListener {
            val isMovieFavorited = isMovieFavorited ?: return@setOnClickListener
            viewModel.saveOrDeleteFavoriteMovie(movie)
            this.isMovieFavorited = !isMovieFavorited
            updateButtonIcon()
        }


    }

    private fun updateButtonIcon() {
        val isMovieFavorited = isMovieFavorited ?: return
        binding.favorite.setImageResource(
            when {
                isMovieFavorited -> R.drawable.ic_favorite
                else -> R.drawable.ic_unfavorite
            }
        )
    }


    companion object {

        private const val MOVIE = "MOVIE"

        fun newInstance(moviesUi: MoviesUi): DetailsFragment {
            val args = Bundle().apply {
                putSerializable(MOVIE, moviesUi)
            }
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}