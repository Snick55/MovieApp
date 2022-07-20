package com.android.movieapp.presentation

import android.util.Log
import com.android.movieapp.R
import com.android.movieapp.core.ResourceManager
import com.android.movieapp.data.HandleError
import com.android.movieapp.domain.EmptyCacheException
import com.android.movieapp.domain.NoInternetConnectionException
import com.android.movieapp.domain.ServiceUnavailableException
import com.android.movieapp.presentation.favorite.FavoriteErrorCommunication
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HandleUiError @Inject constructor(
    private val resourceManager: ResourceManager,
    private val communication: ErrorCommunication,
) : HandleError {

    override fun handle(error: Exception): Exception {
        val errorMessage = when (error) {
            is NoInternetConnectionException -> resourceManager.getMessage(R.string.no_internet_connection)
            is ServiceUnavailableException -> resourceManager.getMessage(R.string.service_unavailable_exception)
            is EmptyCacheException -> resourceManager.getMessage(R.string.empty_favorites)
            else -> resourceManager.getMessage(R.string.generic_exception)
        }
        Log.d("ERROR", "ERROR IS $errorMessage")
        communication.show(errorMessage)
        return error
    }
}