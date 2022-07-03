package com.android.movieapp.data

import com.android.movieapp.data.cloud.CloudDataSource
import com.android.movieapp.data.cloud.MoviesCloud
import com.android.movieapp.domain.MoviesDomain
import javax.inject.Inject
import javax.inject.Singleton

interface Repository {

    suspend fun getMovies(): MoviesDomain


    @Singleton
    class Base @Inject constructor(
        private val cloudDataSource: CloudDataSource,
        private val mapper: MoviesData.Mapper<MoviesDomain>,
        private val mapperData: MoviesCloud.Mapper<MoviesData>

    ) : Repository {
        override suspend fun getMovies(): MoviesDomain {
            val moviesData = cloudDataSource.getMovies().map(mapperData)
            return moviesData.map(mapper)
        }
    }
}