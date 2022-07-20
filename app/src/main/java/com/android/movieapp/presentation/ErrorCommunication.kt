package com.android.movieapp.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import javax.inject.Inject
import javax.inject.Singleton

interface ErrorCommunication {

    fun show(errorMessage: String)

    fun observe(lifecycleOwner: LifecycleOwner,observer: Observer<String>)


    class Base @Inject constructor() : ErrorCommunication{
       private val liveData = MutableLiveData<String>()

        override fun show(errorMessage: String) {
            Log.d("ERROR", "error is setting")
            liveData.value = errorMessage
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<String>) {
            Log.d("ERROR", "error  observed")

            liveData.observe(lifecycleOwner,observer)
        }
    }
}