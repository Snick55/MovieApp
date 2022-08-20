package com.android.movieapp.data

import com.android.movieapp.data.cache.CacheDataSource
import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.data.cloud.CloudDataSource
import com.android.movieapp.domain.EmptyCacheException
import com.android.movieapp.domain.MoviesDomain
import com.android.movieapp.presentation.MoviesUi
import javax.inject.Inject
import javax.inject.Singleton

interface Repository {

    suspend fun getMovies(): MoviesDomain

    suspend fun getFavoritesMovies(): List<FavoriteEntity>

    suspend fun saveMovie(movie: FavoriteEntity)

    suspend fun deleteMovie(movie: FavoriteEntity)

    suspend fun isMovieFavorite(movies: MoviesUi): Boolean


    @Singleton
    class Base @Inject constructor(
        private val cloudDataSource: CloudDataSource,
        private val mapper: MoviesData.Mapper<MoviesDomain>,
        private val cacheDataSource: CacheDataSource

    ) : Repository {
        override suspend fun getMovies(): MoviesDomain {
            val moviesData = cloudDataSource.getMovies()
            return moviesData.map(mapper)
        }

        override suspend fun getFavoritesMovies(): List<FavoriteEntity> {
           return cacheDataSource.getFavoriteMovies().ifEmpty { throw EmptyCacheException() }
        }


        override suspend fun saveMovie(movie: FavoriteEntity) {
           cacheDataSource.saveFavorite(movie)
        }

        override suspend fun deleteMovie(movie: FavoriteEntity) {
            cacheDataSource.deleteFavorite(movie)
        }

        override suspend fun isMovieFavorite(movies: MoviesUi): Boolean {
          return  cacheDataSource.isMovieFavorite(movies)
        }
    }
}