package com.android.movieapp.presentation.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.databinding.MovieItemBinding
import com.android.movieapp.presentation.MoviesUi
import com.bumptech.glide.Glide

class FavoriteAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>(), View.OnClickListener {

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
        binding.root.setOnClickListener(this)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount(): Int = favorites.size

    override fun onClick(p0: View) {
        val movie = p0.tag as MoviesUi
        listener.handle(movie)
    }

    interface Listener {
        fun handle(moviesUi: MoviesUi)
    }
}