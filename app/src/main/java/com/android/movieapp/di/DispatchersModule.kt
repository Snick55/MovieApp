package com.android.movieapp.di

import com.android.movieapp.core.Dispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun provideDispatchers(
        dispatchers: Dispatchers.Base
    ): Dispatchers

}