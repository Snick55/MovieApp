package com.android.movieapp.data

interface HandleError {

    fun handle(error: Exception): Exception
}