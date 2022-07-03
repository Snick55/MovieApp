package com.android.movieapp.core

import android.app.Application
import com.android.movieapp.data.MoviesData
import com.android.movieapp.data.Repository
import com.android.movieapp.data.cloud.ApiService
import com.android.movieapp.data.cloud.CloudDataSource
import com.android.movieapp.data.cloud.MoviesCloud
import com.android.movieapp.domain.HandleDomainError
import com.android.movieapp.domain.MoviesDomain
import com.android.movieapp.domain.MoviesInteractor
import com.android.movieapp.presentation.*
import com.android.movieapp.presentation.movies.MoviesViewModel
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class App : Application()