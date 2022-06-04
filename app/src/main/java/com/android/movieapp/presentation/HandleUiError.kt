package com.android.movieapp.presentation

import com.android.movieapp.R
import com.android.movieapp.core.ResourceManager
import com.android.movieapp.data.HandleError
import com.android.movieapp.domain.NoInternetConnectionException
import com.android.movieapp.domain.ServiceUnavailableException

class HandleUiError(
    resourceManager: ResourceManager
): HandleError {

    override fun handle(error: Exception): Exception {
     val errorMessage =  when(error){
          is  NoInternetConnectionException -> R.string.no_internet_connection
         is ServiceUnavailableException -> R.string.service_unavailable_exception
         else -> R.string.generic_exception
         //todo
        }
        return error
    }
}