package com.android.movieapp.presentation

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface ProgressCommunication {

    fun show(visibility: Visibility)

    fun observe(lifecycleOwner: LifecycleOwner,observer: Observer<Visibility>)


    class Base: ProgressCommunication{

        private val liveData = MutableLiveData<Visibility>()

        override fun show(visibility: Visibility) {
            liveData.value = visibility
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<Visibility>) {
            liveData.observe(lifecycleOwner,observer)
        }
    }

}