package com.android.movieapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface ErrorCommunication {

    fun show(errorMessage: String)

    fun observe(lifecycleOwner: LifecycleOwner,observer: Observer<String>)

    class Base: ErrorCommunication{
       private val liveData = MutableLiveData<String>()

        override fun show(errorMessage: String) {
            liveData.postValue(errorMessage)
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<String>) {
            liveData.observe(lifecycleOwner,observer)
        }
    }
}