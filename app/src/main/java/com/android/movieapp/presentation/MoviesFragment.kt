package com.android.movieapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movieapp.core.App
import com.android.movieapp.databinding.MoviesFragmentBinding

class MoviesFragment : Fragment(){

    private lateinit var binding: MoviesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesFragmentBinding.inflate(inflater,container,false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (requireActivity().applicationContext as App).moviesViewModel

       val adapter = MoviesAdapter()
        binding.moviesRV.adapter = adapter
        binding.moviesRV.layoutManager = GridLayoutManager(requireActivity(),3)


        viewModel.observeMovie(viewLifecycleOwner){
            adapter.setList(it)
        }
        viewModel.observeProgress(viewLifecycleOwner){
            it.apply (binding.progressLayout)
        }

    }
}