package com.android.movieapp.presentation

import com.android.movieapp.data.cloud.Film

interface MoviesUi {

    fun films():List<Film>
    class Base(private val films: List<Film>):MoviesUi{
       override fun films() = films
    }
}