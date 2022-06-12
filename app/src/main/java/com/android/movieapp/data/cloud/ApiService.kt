package com.android.movieapp.data.cloud

import retrofit2.http.GET

interface ApiService {
    @GET("sequeniatesttask/films.json")
    suspend fun getMovies(): MoviesCloud.Base

}