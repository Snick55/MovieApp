package com.android.movieapp.presentation.Details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.movieapp.databinding.FragmentDetailsBinding
import com.android.movieapp.presentation.MoviesUi
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDetailsBinding.inflate(inflater,container,false)

        val movie = requireArguments().getSerializable(MOVIE) as MoviesUi
        movie.showImage(binding.imageView)
        movie.showDescription(binding.description)
        return binding.root

    }


    companion object {

        private const val MOVIE = "MOVIE"

        fun newInstance(moviesUi: MoviesUi):DetailsFragment {
            val args = Bundle().apply {
                putSerializable(MOVIE,moviesUi)
            }
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}