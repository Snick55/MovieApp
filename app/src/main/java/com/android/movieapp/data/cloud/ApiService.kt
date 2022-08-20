package com.android.movieapp.data.cloud

import retrofit2.http.GET

interface ApiService {
    @GET("/shows")
    suspend fun getMovies(): List<Film>

}