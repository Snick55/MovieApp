package com.android.movieapp.domain

import com.android.movieapp.core.Dispatchers
import com.android.movieapp.core.MovieMapper
import com.android.movieapp.data.HandleError
import com.android.movieapp.data.Repository
import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.di.UiError
import com.android.movieapp.presentation.MoviesUi
import javax.inject.Inject

interface FavoriteInteractor {

    suspend fun saveMovie(movie: MoviesUi)

    suspend fun deleteMovie(movie: MoviesUi)

    suspend fun isMovieFavorite(movie: MoviesUi): Boolean

    suspend fun getFavoritesMovies(): List<MoviesUi>

    class Base @Inject constructor(
        private val repository: Repository,
        private val mapper: MovieMapper<MoviesUi>,
        @UiError private val handleError: HandleError
    ) : FavoriteInteractor {
        override suspend fun saveMovie(movie: MoviesUi) {
            repository.saveMovie(movie.toFavorite())
        }

        override suspend fun deleteMovie(movie: MoviesUi) {
            repository.deleteMovie(movie.toFavorite())
        }

        override suspend fun isMovieFavorite(movie: MoviesUi): Boolean {
            return repository.isMovieFavorite(movie)
        }

        override suspend fun getFavoritesMovies(): List<MoviesUi> {
           val movies =  try {
                  repository.getFavoritesMovies().map {
                    it.map(mapper)
                }
            } catch (e: Exception) {
                handleError.handle(e)
               emptyList()
            }
            return movies
        }


    }

}