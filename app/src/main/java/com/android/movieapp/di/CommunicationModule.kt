package com.android.movieapp.di

import com.android.movieapp.presentation.*
import com.android.movieapp.presentation.favorite.FavoriteCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommunicationModule {


    @Binds
    abstract fun bindErrorCommunication(
        errorCommunication: ErrorCommunication.Base
    ): ErrorCommunication

    @Binds
    abstract fun bindProgressCommunication(
        progressCommunication: ProgressCommunication.Base
    ): ProgressCommunication

    @Binds
    abstract fun bindCommunication(
        communication: Communication.Base
    ): Communication

    @Binds
    abstract fun bindCurrentMovieCommunication(
        currentMovieCommunication: CurrentMovieCommunication.Base
    ): CurrentMovieCommunication

    @Binds
    abstract fun bindFavoriteCommunication(
        favoriteCommunication: FavoriteCommunication.Base
    ): FavoriteCommunication

}