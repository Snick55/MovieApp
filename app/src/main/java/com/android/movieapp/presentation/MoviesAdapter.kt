package com.android.movieapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.data.cloud.Film
import com.android.movieapp.databinding.MovieItemBinding
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    private var films: List<Film> = emptyList()


    fun setList(movies: List<Film>) {
        films = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val poster = films[position].imageUrl
        val name = films[position].localizedName
        with(holder.binding) {
            root.tag = films[position]
            nameItem.text = name
            Glide.with(root.context).load(poster).into(background)
        }
    }

    override fun getItemCount(): Int = films.size

    class MyViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

}