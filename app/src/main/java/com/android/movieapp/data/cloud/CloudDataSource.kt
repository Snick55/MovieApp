package com.android.movieapp.data.cloud

import android.util.Log
import com.android.movieapp.data.HandleError

interface CloudDataSource {
    suspend fun getMovies(): MoviesCloud


    class Base(
        private val service: ApiService,
        private val handleError: HandleError
    ) : CloudDataSource {
        override suspend fun getMovies(): MoviesCloud =
            try {
                service.getMovies()
            } catch (e: Exception) {
                Log.d("TAG","ERROR${e}")
                throw handleError.handle(e)
            }
    }
}