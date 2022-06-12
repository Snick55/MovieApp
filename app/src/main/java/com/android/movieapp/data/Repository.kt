package com.android.movieapp.data

import android.util.Log
import com.android.movieapp.data.cloud.CloudDataSource
import com.android.movieapp.data.cloud.MoviesCloud
import com.android.movieapp.domain.MoviesDomain

interface Repository {

    suspend fun getMovies(): MoviesDomain


    class Base(
        private val cloudDataSource: CloudDataSource,
        private val mapper: MoviesData.Mapper<MoviesDomain>,
        private val mapperData: MoviesCloud.Mapper<MoviesData>

        ) : Repository {
        override suspend fun getMovies(): MoviesDomain {
            val moviesData = cloudDataSource.getMovies().map(mapperData)
            Log.d("TAG","MOVIESDATA:::::::::::::: ${moviesData.test()}")
            return moviesData.map(mapper)
        }
    }
}