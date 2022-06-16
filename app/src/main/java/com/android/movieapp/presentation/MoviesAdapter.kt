package com.android.movieapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.android.movieapp.databinding.CategoryItemBinding
import com.android.movieapp.databinding.MovieItemBinding
import com.bumptech.glide.Glide
import java.lang.IllegalStateException

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val uiMovies: MutableList<UiMovie> = ArrayList<UiMovie>()

    fun setList(movies: List<UiMovie>) {
        this.uiMovies.clear()
        this.uiMovies.addAll(movies)
        // todo use diff util callback
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        val currentUiMovie = uiMovies[position]
        return when(currentUiMovie) {
            is UiMovie.Base -> 1
            is UiMovie.Category -> 2
            else -> throw IllegalStateException("Unprocessed movie $currentUiMovie")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return when(viewType) {
            1 -> {
                // todo move the shared code for Do not repeat yourself (DRY)
                val inflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(inflater, parent, false)
                ViewHolder.Movie(binding)
            }
            2 -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CategoryItemBinding.inflate(inflater, parent, false)
                ViewHolder.Category(binding)
            }
           else -> throw IllegalStateException("Unprocessed viewType $viewType")

       }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = uiMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = uiMovies.size

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(uiMovie: UiMovie) = Unit

        class Movie(
            private val binding: MovieItemBinding
        ) : ViewHolder(binding.root) {
            override fun bind(uiMovie: UiMovie) {
                with(binding) {
                    root.tag = uiMovie
                    uiMovie.showTitle(nameItem)
                    uiMovie.showImage(background)
                }
            }
        }

        class Category(
            private val binding: CategoryItemBinding
        ) : ViewHolder(binding.root) {

            override fun bind(uiMovie: UiMovie) {
                with(binding) {
                    root.tag = uiMovie
                    uiMovie.showCategory(categoryTextView)
                }
            }
        }
    }
}