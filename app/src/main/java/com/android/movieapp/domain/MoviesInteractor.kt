package com.android.movieapp.domain

import com.android.movieapp.core.Dispatchers
import com.android.movieapp.data.HandleError
import com.android.movieapp.data.Repository
import com.android.movieapp.di.UiError
import com.android.movieapp.presentation.MoviesUi
import javax.inject.Inject
import javax.inject.Singleton

interface MoviesInteractor: Interactor {

    suspend fun movies(
        atFinish: () -> Unit,
        successful: (List<MoviesUi>) -> Unit
    )

    @UiError
    @Singleton
    class Base @Inject constructor (
        private val mapper: MoviesDomain.Mapper.Base,
        private val repository: Repository,
        dispatchers: Dispatchers,
     @UiError   handleError: HandleError
    ) : Interactor.Abstract(dispatchers, handleError), MoviesInteractor {

        override suspend fun movies(
            atFinish:  () -> Unit,
            successful:  (List<MoviesUi>) -> Unit
        ) = handle(successful, atFinish) {
                val data = repository.getMovies()
                return@handle data.map(mapper)
            }
    }

}