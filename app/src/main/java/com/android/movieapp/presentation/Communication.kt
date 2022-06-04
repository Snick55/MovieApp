package com.android.movieapp.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.movieapp.data.cloud.Film

interface Communication {

    fun show(moviesUi: MoviesUi)

    fun observe(lifecycleOwner: LifecycleOwner,observer: Observer<List<Film>>)



    class  Base: Communication{

        private val liveData = MutableLiveData<List<Film>>()

        override fun show(moviesUi: MoviesUi) {
            Log.d("TAG","LIVEDATA VALUE IS::::${moviesUi.films()}")
            liveData.value = moviesUi.films()
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<Film>>) {
            liveData.observe(lifecycleOwner,observer)
        }
    }

}