package com.android.movieapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.movieapp.data.Repository
import com.android.movieapp.domain.FavoriteInteractor
import com.android.movieapp.presentation.MoviesUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val interactor: FavoriteInteractor
): ViewModel() {


   suspend fun isMovieFavorite(movie: MoviesUi): Boolean = interactor.isMovieFavorite(movie)

    fun saveOrDeleteFavoriteMovie(movie: MoviesUi) = viewModelScope.launch {
        if (interactor.isMovieFavorite(movie)){
            interactor.deleteMovie(movie)
        }else
            interactor.saveMovie(movie)
    }


}