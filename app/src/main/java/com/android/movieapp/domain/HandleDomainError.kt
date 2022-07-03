package com.android.movieapp.domain

import com.android.movieapp.data.HandleError
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton





@Singleton
class HandleDomainError @Inject constructor():HandleError {

    override fun handle(error: Exception) =
        if (error is UnknownHostException)

            NoInternetConnectionException()
        else
            ServiceUnavailableException()
}