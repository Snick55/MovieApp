package com.android.movieapp.presentation.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.android.movieapp.MainActivity
import com.android.movieapp.R
import com.android.movieapp.databinding.MoviesFragmentBinding
import com.android.movieapp.presentation.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(){

    private lateinit var binding: MoviesFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesFragmentBinding.inflate(inflater,container,false)

        Log.d("ERROR","fragment create")

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).changeTitle(getString(R.string.movies_title))

        val viewModel by viewModels<MoviesViewModel>()

       val adapter = MoviesAdapter(viewModel)
        binding.moviesRV.adapter = adapter
        binding.moviesRV.layoutManager = GridLayoutManager(requireActivity(),3)


        viewModel.observeMovie(viewLifecycleOwner){
            binding.errorMessage.visibility = View.INVISIBLE
            adapter.setList(it)
        }
        viewModel.observeProgress(viewLifecycleOwner){
            it.apply(binding.progressLayout)
        }
        viewModel.observeError(viewLifecycleOwner){
            binding.errorMessage.text = it
        }

        viewModel.observeCurrentMovie(viewLifecycleOwner){ event ->
            event?.get()?.let {
                // TODO: 19.06.2022 add navigation components
                val fragment = DetailsFragment.newInstance(it)
                parentFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.fragment_container,fragment)
                    .commit()
            }

        }

    }
    
    
    



}