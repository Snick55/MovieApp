package com.android.movieapp.core

interface Movie {

    fun <T> map(mapper: MovieMapper<T>): T
}