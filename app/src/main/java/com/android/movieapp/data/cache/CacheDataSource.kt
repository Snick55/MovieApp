package com.android.movieapp.data.cache

import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.presentation.MoviesUi
import javax.inject.Inject

interface CacheDataSource {

    suspend fun getFavoriteMovies(): List<FavoriteEntity>

    suspend fun saveFavorite(movie: FavoriteEntity)

    suspend fun deleteFavorite(movie: FavoriteEntity)

    suspend fun isMovieFavorite(movie: MoviesUi): Boolean

    class Base @Inject constructor(private val moviesDao: MoviesDao): CacheDataSource{
        override suspend fun getFavoriteMovies(): List<FavoriteEntity> {
            return moviesDao.getAllFavorites()
        }

        override suspend fun saveFavorite(movie: FavoriteEntity) {
           moviesDao.saveFavoriteMovie(movie)
        }

        override suspend fun deleteFavorite(movie: FavoriteEntity) {
           moviesDao.deleteFavoriteMovie(movie)
        }

        override suspend fun isMovieFavorite(movie: MoviesUi): Boolean {
            return moviesDao.getMovieById(movie.getId()) != null
        }
    }


}