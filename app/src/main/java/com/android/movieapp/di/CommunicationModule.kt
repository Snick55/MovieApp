package com.android.movieapp.di

import com.android.movieapp.presentation.Communication
import com.android.movieapp.presentation.CurrentMovieCommunication
import com.android.movieapp.presentation.ErrorCommunication
import com.android.movieapp.presentation.ProgressCommunication
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

}