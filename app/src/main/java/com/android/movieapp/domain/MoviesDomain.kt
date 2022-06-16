package com.android.movieapp.domain

import com.android.movieapp.core.MovieMapper
import com.android.movieapp.data.cloud.Film
import com.android.movieapp.presentation.MoviesUi
import com.android.movieapp.presentation.UiMovie

interface MoviesDomain{

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        val films: List<Film>
    ) : MoviesDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(films)
    }


    interface Mapper<T> {

        fun map(films: List<Film>): T

        class Base(
            private val filmToUiMovieMapper: MovieMapper<UiMovie>
        ) : Mapper<List<UiMovie>> {

            override fun map(films: List<Film>): List<UiMovie> {
                return films.map { film -> film.map(filmToUiMovieMapper) }
            }
        }

    }

}
