package com.android.movieapp.presentation

import android.util.Log
import android.widget.ImageView
import android.widget.TextView

interface UiMovie {
    // ты можешь обЪединить эти два метода в один,для понимания я создам их два и нарушу DRY
    fun showTitle(titleItem: TextView) = Unit
    fun showCategory(categoryItem: TextView) = Unit

    fun showImage(imageMovie: ImageView) = Unit

    data class Base(
        private val title: String,
        private val description: String,
        private val imageUrl: String
    ) : UiMovie {

        override fun showTitle(titleItem: TextView) {
            titleItem.text = title
        }

        override fun showImage(imageMovie: ImageView) {
            com.bumptech.glide.Glide.with(imageMovie.context).load(imageUrl).into(imageMovie)
        }
    }

    data class Category(
        private val category: String
    ) : UiMovie {

        override fun showCategory(categoryItem: TextView) {
            categoryItem.text = category
        }
    }
}