package com.android.movieapp.domain

import com.android.movieapp.core.MovieMapper
import com.android.movieapp.data.cloud.Film
import com.android.movieapp.presentation.MoviesUi
import javax.inject.Inject
import javax.inject.Singleton

interface MoviesDomain{

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val films: List<Film>
    ) : MoviesDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(films)
    }


    interface Mapper<T> {

        fun map(films: List<Film>): T

        @Singleton
        class Base @Inject constructor (
         private val  mapperMovie:MovieMapper<MoviesUi>
        ): Mapper<List<MoviesUi>> {
            override fun map(films: List<Film>): List<MoviesUi> {
                return films.map { film-> film.map(mapperMovie) }
            }
        }

    }

}
