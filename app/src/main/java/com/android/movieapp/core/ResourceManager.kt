package com.android.movieapp.core

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {

    fun getMessage(@StringRes id: Int): String

    class Base(private val context: Context): ResourceManager{
        override fun getMessage(id: Int): String {
            return context.getString(id)
        }
    }

}