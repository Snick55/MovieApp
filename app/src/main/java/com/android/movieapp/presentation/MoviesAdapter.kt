package com.android.movieapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.android.movieapp.data.cloud.Film
import com.android.movieapp.databinding.CategoryItemBinding
import com.android.movieapp.databinding.MovieItemBinding
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    private var films: List<Film> = emptyList()


    fun setList(movies: List<Film>) {
        films = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return if (viewType == 0) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CategoryItemBinding.inflate(inflater, parent, false)
            MyViewHolder(binding)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val binding = MovieItemBinding.inflate(inflater, parent, false)
            MyViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (holder.binding is MovieItemBinding) {
            val poster = films[position].imageUrl
            val name = films[position].localizedName
            with(holder.binding) {
                root.tag = films[position]
                nameItem.text = name
                Glide.with(root.context).load(poster).into(background)
            }
        }
    }

    override fun getItemCount(): Int = films.size 

    override fun getItemViewType(position: Int): Int {
        // TODO: 13.06.2022  
        return if (position == 0 || position == 1 || position == 2) 0
        else 1
    }

    class MyViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)


}