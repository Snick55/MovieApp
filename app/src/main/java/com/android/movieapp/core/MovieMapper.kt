package com.android.movieapp.core

import com.android.movieapp.presentation.MoviesUi


interface MovieMapper<T> {

    fun map(title: String,description: String,imageUrl: String,year: Int,rating: Double):T

    class FilmToMoviesUiMapper(private val resourceManager: ResourceManager):MovieMapper<MoviesUi>{

        override fun map(title: String, description: String, imageUrl: String,year: Int,rating: Double): MoviesUi {
            return MoviesUi.Base(title, description, imageUrl,year, rating,resourceManager)
        }
    }


}