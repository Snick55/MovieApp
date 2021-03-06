package com.android.movieapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton

interface CurrentMovieCommunication {

    fun show(movieUi: MoviesUi)

    fun observe(owner: LifecycleOwner,observer: Observer<Event<MoviesUi>>)

    @Singleton
    class Base @Inject constructor() : CurrentMovieCommunication{

        private val liveData = MutableLiveData<Event<MoviesUi>>()

        override fun show(movieUi: MoviesUi) {
           liveData.value = Event(movieUi)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Event<MoviesUi>>) {
            liveData.observe(owner,observer)
        }
    }

    class Event<T>(
        value: T
    ){

        private var _value: T? = value

        fun get(): T? = _value.also { _value = null }

    }
}