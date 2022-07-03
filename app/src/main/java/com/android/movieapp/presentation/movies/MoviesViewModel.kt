package com.android.movieapp.presentation.movies

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.movieapp.domain.Interactor
import com.android.movieapp.domain.MoviesInteractor
import com.android.movieapp.presentation.*
import com.android.movieapp.presentation.CurrentMovieCommunication.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.ArrayList
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val interactor: MoviesInteractor,
    private val progressCommunication: ProgressCommunication,
    private val communication: Communication,
    private val errorCommunication: ErrorCommunication,
    private val currentMovieCommunication: CurrentMovieCommunication,
    dispatcher: com.android.movieapp.core.Dispatchers
) : ViewModel(),MoviesAdapter.Listener {


    private val atFinish = {
        progressCommunication.show(Visibility.InVisible())
    }


    init {
        progressCommunication.show(Visibility.Visible())
        dispatcher.launchBackground(viewModelScope) {
            interactor.movies(atFinish) {

                val moviesUiWithCategory = ArrayList<MoviesUi>()

                moviesUiWithCategory.addAll(
                    listOf(
                        MoviesUi.Category("Новинки"),
                        MoviesUi.Category(""),
                        MoviesUi.Category("")
                    )
                )
                moviesUiWithCategory.addAll(it)

                communication.show(moviesUiWithCategory)
            }
        }
    }

    override fun handle(moviesUi: MoviesUi) {
        currentMovieCommunication.show(moviesUi)
    }

    fun observeMovie(lifecycleOwner: LifecycleOwner, observer: Observer<List<MoviesUi>>) {
        communication.observe(lifecycleOwner, observer)
    }

    fun observeProgress(lifecycleOwner: LifecycleOwner, observer: Observer<Visibility>) {
        progressCommunication.observe(lifecycleOwner, observer)
    }

    fun observeError(lifecycleOwner: LifecycleOwner, observer: Observer<String>) {
        errorCommunication.observe(lifecycleOwner, observer)
    }

    fun observeCurrentMovie(lifecycleOwner: LifecycleOwner,observer: Observer<Event<MoviesUi>>){
        currentMovieCommunication.observe(lifecycleOwner,observer)
    }

}