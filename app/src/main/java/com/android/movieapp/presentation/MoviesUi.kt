package com.android.movieapp.presentation

import android.widget.ImageView
import android.widget.TextView
import com.android.movieapp.data.cloud.Film
import com.bumptech.glide.Glide
import java.io.Serializable

interface MoviesUi : Serializable {

    fun show(textView: TextView) = Unit

    fun showDescription(textView: TextView) = Unit

    fun showImage(imageView: ImageView) = Unit


    data class Base(
        private val title: String,
        private val description: String,
        private val imageUrl: String
    ) : MoviesUi {
        override fun show(textView: TextView) {
            textView.text = title
        }

        override fun showDescription(textView: TextView) {
            textView.text = description
        }

        override fun showImage(imageView: ImageView) {
            Glide.with(imageView.context).load(imageUrl).into(imageView)
        }
    }

    data class Category(
        private val category: String
    ): MoviesUi{
        override fun show(textView: TextView) {
            textView.text = category
        }
    }


}