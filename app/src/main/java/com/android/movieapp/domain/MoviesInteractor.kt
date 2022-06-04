package com.android.movieapp.domain

import com.android.movieapp.core.Dispatchers
import com.android.movieapp.data.HandleError
import com.android.movieapp.data.Repository
import com.android.movieapp.presentation.MoviesUi

interface MoviesInteractor {

    suspend fun movies(
        atFinish: () -> Unit,
        successful: (MoviesUi) -> Unit
    )

    class Base(
        private val mapper: MoviesDomain.Mapper<MoviesUi>,
        private val repository: Repository,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : Interactor.Abstract(dispatchers, handleError), MoviesInteractor {

        override suspend fun movies(
            atFinish:  () -> Unit,
            successful:  (MoviesUi) -> Unit
        ) = handle(successful, atFinish) {
                val data = repository.getMovies()
                return@handle data.map(mapper)
            }
    }

}