package com.android.movieapp.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movieapp.MainActivity
import com.android.movieapp.R
import com.android.movieapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeTitle(getString(R.string.favorite_movies))


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = FavoriteAdapter()
        binding.favoriteRecyclerView.adapter = adapter
        binding.favoriteRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 3)
        viewModel.observe(viewLifecycleOwner) {
            adapter.setFavorites(it)
            binding.errorMessage.visibility = View.INVISIBLE
        }


        viewModel.observeError(viewLifecycleOwner) {
            with(binding.errorMessage) {
                visibility = View.VISIBLE
               text = it
            }
        }
    }

}