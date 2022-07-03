package com.android.movieapp.data

import com.android.movieapp.data.cloud.Film
import com.android.movieapp.domain.MoviesDomain
import javax.inject.Inject
import javax.inject.Singleton

interface MoviesData {

    fun <T> map(mapper: Mapper<T>): T

    data class Base @Inject constructor(
        private val films: List<Film>
    ) : MoviesData {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(films)
    }


    interface Mapper<T> {

        fun map(list: List<Film>): T

        @Singleton
        class Base @Inject constructor() : Mapper<MoviesDomain> {
            override fun map(list: List<Film>): MoviesDomain {
                return MoviesDomain.Base(list)
            }
        }

    }

}