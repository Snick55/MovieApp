package com.android.movieapp.core

import com.android.movieapp.presentation.MoviesUi
import javax.inject.Inject
import javax.inject.Singleton


interface MovieMapper<T> {

    fun map(id: Int,title: String,description: String,imageUrl: String,year: Int,rating: Double):T

    @Singleton
    class FilmToMoviesUiMapper @Inject constructor(private val resourceManager: ResourceManager):MovieMapper<MoviesUi>{

        override fun map(id: Int,title: String, description: String, imageUrl: String,year: Int,rating: Double): MoviesUi {
            return MoviesUi.Base(id,title, description, imageUrl,year, rating,resourceManager)
        }
    }


}