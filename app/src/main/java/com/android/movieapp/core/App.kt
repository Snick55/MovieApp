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
import com.android.movieapp.presentation.Communication
import com.android.movieapp.presentation.HandleUiError
import com.android.movieapp.presentation.MoviesViewModel
import com.android.movieapp.presentation.ProgressCommunication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {


    lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate() {
        super.onCreate()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)

        val cloudDataSource = CloudDataSource.Base(service,HandleDomainError())
        val moviesDataMapper = MoviesData.Mapper.Base()
        val moviesCloudMapper = MoviesCloud.Mapper.Base()

        val repository = Repository.Base(cloudDataSource,moviesDataMapper,moviesCloudMapper)

        val moviesDomainMapper = MoviesDomain.Mapper.Base()
        val dispatchers = Dispatchers.Base()
        val resourceManager = ResourceManager.Base(this)
        val handleUiError = HandleUiError(resourceManager)

        val interactor = MoviesInteractor.Base(moviesDomainMapper,repository,dispatchers,handleUiError)

        val progressCommunication = ProgressCommunication.Base()
        val communication = Communication.Base()

        moviesViewModel = MoviesViewModel(interactor,progressCommunication,communication,dispatchers)
    }


}