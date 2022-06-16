package com.android.movieapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {

    fun show(moviesUi: List<UiMovie>)

    fun observe(lifecycleOwner: LifecycleOwner,observer: Observer<List<UiMovie>>)



    class  Base: Communication{

        private val liveData = MutableLiveData<List<UiMovie>>()

        override fun show(moviesUi: List<UiMovie>) {
            liveData.value = moviesUi
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<UiMovie>>) {
            liveData.observe(lifecycleOwner,observer)
        }
    }

}