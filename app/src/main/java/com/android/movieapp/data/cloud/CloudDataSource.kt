package com.android.movieapp.data.cloud

import android.util.Log
import com.android.movieapp.data.HandleError
import com.android.movieapp.di.DomainError
import com.android.movieapp.domain.HandleDomainError
import javax.inject.Inject
import javax.inject.Singleton

interface CloudDataSource {
    suspend fun getMovies(): MoviesCloud


    @Singleton
    class Base @Inject constructor (
        private val service: ApiService,
       @DomainError private val handleError: HandleError
    ) : CloudDataSource {
        override suspend fun getMovies(): MoviesCloud =
            try {
                service.getMovies()
            } catch (e: Exception) {
                throw handleError.handle(e)
            }
    }
}