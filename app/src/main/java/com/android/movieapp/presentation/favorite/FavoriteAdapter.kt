package com.android.movieapp.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.databinding.MovieItemBinding
import com.android.movieapp.presentation.MoviesUi
import com.bumptech.glide.Glide

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    private var favorites = emptyList<MoviesUi>()

    fun setFavorites(movies: List<MoviesUi>) {
        favorites = movies
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesUi) {

            with(binding) {
                root.tag = movie
                movie.show(nameItem)
                movie.showImage(background)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount(): Int = favorites.size
}