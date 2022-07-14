package com.android.movieapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.android.movieapp.MainActivity
import com.android.movieapp.R
import com.android.movieapp.databinding.FragmentDetailsBinding
import com.android.movieapp.presentation.MoviesUi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

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

        val movie = requireArguments().getSerializable(MOVIE) as MoviesUi
        movie.showImage(binding.imageView)

        movie.showDetails(
            binding.titleTextview, binding.description,
            binding.dateTextview, binding.rattingTextview,
            R.string.ratting_of_film, R.string.year_of_film
        )

        return binding.root

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