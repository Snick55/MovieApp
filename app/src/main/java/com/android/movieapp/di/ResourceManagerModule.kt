package com.android.movieapp.di

import com.android.movieapp.MainActivity
import com.android.movieapp.core.ResourceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceManagerModule {

    @Binds
    abstract fun bindResourceManager(
        resourceManager: ResourceManager.Base
    ):ResourceManager


}