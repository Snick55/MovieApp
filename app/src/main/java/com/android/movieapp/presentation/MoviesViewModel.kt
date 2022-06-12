package com.android.movieapp.presentation

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.movieapp.data.cloud.Film
import com.android.movieapp.domain.MoviesInteractor

class MoviesViewModel(
    private val interactor: MoviesInteractor,
    private val progressCommunication: ProgressCommunication,
    private val communication: Communication,
    private val errorCommunication: ErrorCommunication,
    dispatcher: com.android.movieapp.core.Dispatchers
): ViewModel() {


    private val atFinish ={
        progressCommunication.show(Visibility.InVisible())
    }


    init {
        progressCommunication.show(Visibility.Visible())
        dispatcher.launchBackground(viewModelScope){
            interactor.movies(atFinish){
                communication.show(it)
            }
        }
    }

    fun observeMovie(lifecycleOwner: LifecycleOwner,observer: Observer<List<Film>>){
        communication.observe(lifecycleOwner, observer)
    }

    fun observeProgress(lifecycleOwner: LifecycleOwner,observer: Observer<Visibility>){
        progressCommunication.observe(lifecycleOwner, observer)
    }

    fun observeError(lifecycleOwner: LifecycleOwner,observer: Observer<String>){
        errorCommunication.observe(lifecycleOwner, observer)
    }

}