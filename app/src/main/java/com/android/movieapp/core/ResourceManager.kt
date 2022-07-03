package com.android.movieapp.core

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


interface ResourceManager {

    fun getMessage(@StringRes id: Int,value: Any? = null): String

    class Base @Inject constructor(@ApplicationContext private val context: Context): ResourceManager{
        override fun getMessage(id: Int,value: Any?): String {
            return  if (value == null) context.getString(id)
            else context.getString(id,value)
        }
    }

}