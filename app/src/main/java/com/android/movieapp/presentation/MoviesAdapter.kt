package com.android.movieapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movieapp.databinding.CategoryItemBinding
import com.android.movieapp.databinding.MovieItemBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    private var films: List<MoviesUi> = emptyList()


    fun setList(movies: List<MoviesUi>) {
        films = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return when (viewType) {
            0 -> {
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = MovieItemBinding.inflate(inflater, parent, false)
                    MyViewHolder.Movie(binding)
                }
                1 -> {
                    val inflater = LayoutInflater.from(parent.context)
                    val binding = CategoryItemBinding.inflate(inflater, parent, false)
                  MyViewHolder.Category(binding)
            }
            else -> throw IllegalStateException("Unprocessed viewType $viewType")

        }
    }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
           holder.bind(films[position])
        }

        override fun getItemCount(): Int = films.size

        override fun getItemViewType(position: Int): Int {
            return if (films[position] is MoviesUi.Category) 1
            else 0
        }

        abstract class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            open fun bind(ui: MoviesUi) = Unit

            class Movie(private val binding: MovieItemBinding) : MyViewHolder(binding.root) {
                override fun bind(ui: MoviesUi) {
                    with(binding) {
                        root.tag = ui
                        ui.show(nameItem)
                        ui.showImage(background)
                    }
                }
            }

            class Category(private val binding: CategoryItemBinding) : MyViewHolder(binding.root) {
                override fun bind(ui: MoviesUi) {
                    ui.show(binding.categoryTextView)
                }
            }
        }
    }
