package com.android.movieapp.di

import com.android.movieapp.data.cloud.CloudDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindCloudDataSource(
        cloudDataSource: CloudDataSource.Base
    ):CloudDataSource

}