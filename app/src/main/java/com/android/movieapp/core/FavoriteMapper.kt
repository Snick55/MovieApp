package com.android.movieapp.core

interface FavoriteMapper {

    fun <T> map(mapper: MovieMapper<T>): T

}