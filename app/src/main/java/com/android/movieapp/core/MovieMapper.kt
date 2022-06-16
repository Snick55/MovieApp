package com.android.movieapp.core

import android.util.Log
import com.android.movieapp.presentation.UiMovie

interface MovieMapper<T> {

    fun map(title: String,description: String,imageUrl: String) : T

    class FilmToUiMovieMapper : MovieMapper<UiMovie> {

        override fun map(title: String, description: String, imageUrl: String): UiMovie
        {
            val movie = UiMovie.Base(title, description, imageUrl)
//            Log.d("zinoviewk","map movie $movie")
            return movie
        }
    }
}