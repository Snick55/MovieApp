package com.android.movieapp.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movieapp.MainActivity
import com.android.movieapp.R
import com.android.movieapp.databinding.FragmentFavoriteBinding
import com.android.movieapp.presentation.MoviesUi
import com.android.movieapp.presentation.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment(),FavoriteAdapter.Listener {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeTitle(getString(R.string.favorite_movies))
        Log.d("LIVE","OnCreateView")
        viewModel.getMovies()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = FavoriteAdapter(this)
        binding.favoriteRecyclerView.adapter = adapter
        binding.favoriteRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 3)
        viewModel.observe(viewLifecycleOwner) {
            adapter.setFavorites(it)
            binding.errorMessage.visibility = View.INVISIBLE
        }


            Log.d("LIVE","OnViewCreated")
            viewModel.observeError(viewLifecycleOwner) {
                with(binding.errorMessage) {
                    visibility = View.VISIBLE
                    text = it
                }
            }



    }

    override fun handle(moviesUi: MoviesUi) {
        val fragment = DetailsFragment.newInstance(moviesUi)
        parentFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragment_container,fragment)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("LIVE","OnDestroyView")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIVE","OnDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("LIVE","OnDetach")
    }

}