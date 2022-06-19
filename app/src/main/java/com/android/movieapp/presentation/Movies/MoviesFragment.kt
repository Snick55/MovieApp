package com.android.movieapp.presentation.Movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movieapp.R
import com.android.movieapp.core.App
import com.android.movieapp.databinding.MoviesFragmentBinding
import com.android.movieapp.presentation.Details.DetailsFragment
import com.android.movieapp.presentation.MoviesUi

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

       val adapter = MoviesAdapter(viewModel)
        binding.moviesRV.adapter = adapter
        binding.moviesRV.layoutManager = GridLayoutManager(requireActivity(),3)


        viewModel.observeMovie(viewLifecycleOwner){
            adapter.setList(it)
        }
        viewModel.observeProgress(viewLifecycleOwner){
            it.apply (binding.progressLayout)
        }
        viewModel.observeError(viewLifecycleOwner){
            binding.errorContainer.visibility = View.VISIBLE
            binding.errorMessage.text = it
        }

        viewModel.observeCurrentMovie(viewLifecycleOwner){ event ->
            event?.get()?.let {
                // TODO: 19.06.2022 add navigation components
                val fragment = DetailsFragment.newInstance(it)
                parentFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.fragment_container,fragment).commit()
            }

        }

    }
    
    
    



}