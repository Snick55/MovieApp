package com.android.movieapp.data

import com.android.movieapp.data.cloud.Film
import com.android.movieapp.domain.MoviesDomain

interface MoviesData {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val films: List<Film>
    ) : MoviesData {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(films)

    }


    interface Mapper<T> {

        fun map(list: List<Film>): T

        class Base() : Mapper<MoviesDomain> {
            override fun map(list: List<Film>): MoviesDomain {
                return MoviesDomain.Base(list)
            }
        }

    }

}