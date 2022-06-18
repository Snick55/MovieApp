package com.android.movieapp.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.movieapp.data.cloud.Film

interface Communication {

    fun show(moviesUi:List <MoviesUi>)

    fun observe(lifecycleOwner: LifecycleOwner,observer: Observer<List<MoviesUi>>)



    class  Base: Communication{

        private val liveData = MutableLiveData<List<MoviesUi>>()

        override fun show(moviesUi:List <MoviesUi>) {
            Log.d("TAG"," TEST${moviesUi[1]}")
            liveData.value = moviesUi
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<MoviesUi>>) {
            liveData.observe(lifecycleOwner,observer)
        }
    }

}