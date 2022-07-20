package com.android.movieapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import javax.inject.Inject

interface ProgressCommunication {

    fun show(visibility: Visibility)

    fun observe(lifecycleOwner: LifecycleOwner,observer: Observer<Visibility>)



    class Base @Inject constructor() : ProgressCommunication{

        private val liveData = MutableLiveData<Visibility>()

        override fun show(visibility: Visibility) {
            liveData.value = visibility
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<Visibility>) {
            liveData.observe(lifecycleOwner,observer)
        }
    }

}