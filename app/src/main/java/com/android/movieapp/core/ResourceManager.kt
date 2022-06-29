package com.android.movieapp.core

import android.content.Context
import androidx.annotation.StringRes


interface ResourceManager {

    fun getMessage(@StringRes id: Int,value: Any? = null): String

    class Base(private val context: Context): ResourceManager{
        override fun getMessage(id: Int,value: Any?): String {
            return  if (value == null) context.getString(id)
            else context.getString(id,value)
        }
    }

}