package com.android.movieapp.core

import com.android.movieapp.presentation.MoviesUi

interface MovieMapper<T> {

    fun map(title: String,description: String,imageUrl: String):T

    class FilmToMoviesUiMapper:MovieMapper<MoviesUi>{

        override fun map(title: String, description: String, imageUrl: String): MoviesUi {
            return MoviesUi.Base(title, description, imageUrl)
        }
    }


}