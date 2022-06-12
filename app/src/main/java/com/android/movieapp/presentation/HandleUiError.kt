package com.android.movieapp.presentation

import com.android.movieapp.R
import com.android.movieapp.core.ResourceManager
import com.android.movieapp.data.HandleError
import com.android.movieapp.domain.NoInternetConnectionException
import com.android.movieapp.domain.ServiceUnavailableException

class HandleUiError(
  private val resourceManager: ResourceManager,
   private val communication: ErrorCommunication
): HandleError {

    override fun handle(error: Exception): Exception {
     val errorMessage =  when(error){
          is  NoInternetConnectionException ->resourceManager.getMessage(R.string.no_internet_connection)
         is ServiceUnavailableException -> resourceManager.getMessage(R.string.service_unavailable_exception)
         else -> resourceManager.getMessage(R.string.generic_exception)
        }
        communication.show(errorMessage)
        return error
    }
}