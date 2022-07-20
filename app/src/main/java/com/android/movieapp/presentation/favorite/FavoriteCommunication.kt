package com.android.movieapp.presentation.favorite

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.presentation.MoviesUi
import javax.inject.Inject

interface FavoriteCommunication {

    fun show(movies: List<MoviesUi>)

    fun observe(owner: LifecycleOwner,observer: Observer<List<MoviesUi>>)


    class Base @Inject constructor(): FavoriteCommunication{
        private val liveData = MutableLiveData<List<MoviesUi>>()

        override fun show(movies: List<MoviesUi>) {
            liveData.value = movies
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<MoviesUi>>) {
            liveData.observe(owner, observer)
        }
    }


}