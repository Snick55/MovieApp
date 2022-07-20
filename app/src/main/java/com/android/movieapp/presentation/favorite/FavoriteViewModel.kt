package com.android.movieapp.presentation.favorite

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.domain.FavoriteInteractor
import com.android.movieapp.presentation.ErrorCommunication
import com.android.movieapp.presentation.MoviesUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val interactor: FavoriteInteractor,
    private val communication: FavoriteCommunication,
    private val errorCommunication: ErrorCommunication
): ViewModel() {


    init {
        viewModelScope.launch {
            val movies = interactor.getFavoritesMovies()

            communication.show(movies)
        }
    }


    fun observe(owner: LifecycleOwner,observer: Observer<List<MoviesUi>>){
        communication.observe(owner, observer)
    }

    fun observeError(owner: LifecycleOwner,observer: Observer<String>){
        errorCommunication.observe(owner,observer)
    }
}