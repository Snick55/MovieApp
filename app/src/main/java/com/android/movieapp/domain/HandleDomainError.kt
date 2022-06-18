package com.android.movieapp.domain

import com.android.movieapp.data.HandleError
import java.net.UnknownHostException

class HandleDomainError:HandleError {

    override fun handle(error: Exception) =
        if (error is UnknownHostException)

            NoInternetConnectionException()
        else
            ServiceUnavailableException()
}