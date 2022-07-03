package com.android.movieapp.di

import com.android.movieapp.data.HandleError
import com.android.movieapp.domain.HandleDomainError
import com.android.movieapp.presentation.HandleUiError
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DomainError

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UiError


@Module
@InstallIn(SingletonComponent::class)
abstract class HandleErrorModule {

    @DomainError
    @Binds
    abstract fun bindDomainError(
        handleUiError: HandleDomainError
    ): HandleError

    @UiError
    @Binds
    abstract fun bindUiError(
        handleUiError: HandleUiError
    ): HandleError

}