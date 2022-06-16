package com.android.movieapp.domain

import com.android.movieapp.core.Dispatchers
import com.android.movieapp.data.HandleError
import com.android.movieapp.data.Repository

interface MoviesInteractor {

    suspend fun movies(
        atFinish: () -> Unit,
        successful: (MoviesDomain) -> Unit
    )

    class Base(
        private val repository: Repository,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : Interactor.Abstract(dispatchers, handleError), MoviesInteractor {

        override suspend fun movies(
            atFinish:  () -> Unit,
            successful:  (MoviesDomain) -> Unit
        ) = handle(successful, atFinish) {
            return@handle repository.getMovies()
        }
    }

}