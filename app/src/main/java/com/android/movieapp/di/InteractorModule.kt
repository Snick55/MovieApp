package com.android.movieapp.di

import com.android.movieapp.domain.FavoriteInteractor
import com.android.movieapp.domain.Interactor
import com.android.movieapp.domain.MoviesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindInteractor(
        interactor: MoviesInteractor.Base
    ): MoviesInteractor

    @Binds
    abstract fun bindFavoriteInteracror(
        interactor: FavoriteInteractor.Base
    ): FavoriteInteractor

}