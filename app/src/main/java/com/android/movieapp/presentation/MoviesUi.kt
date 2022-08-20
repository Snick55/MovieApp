package com.android.movieapp.presentation

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.android.movieapp.R
import com.android.movieapp.core.ResourceManager
import com.android.movieapp.data.cache.entity.FavoriteEntity
import com.android.movieapp.data.cloud.Film
import com.bumptech.glide.Glide
import java.io.Serializable

interface MoviesUi : Serializable {

    fun getId(): Int = -1

    fun toFavorite() = FavoriteEntity(-1,"",0,0.0,"","")

    fun show(textView: TextView) = Unit

    fun showDescription(textView: TextView) = Unit

    fun showImage(imageView: ImageView) = Unit

    fun showDetails(titleView: TextView, descriptionView: TextView,
                    yearView: TextView, ratingView: TextView,
                    @StringRes ratingResId: Int, @StringRes yearResId:Int ) = Unit


    data class Base(
        private val id: Int,
        private val title: String,
        private val description: String,
        private val imageUrl: String,
        private val year: Int,
        private val rating: Double,
        private val resourceManager: ResourceManager
    ) : MoviesUi {

     override   fun toFavorite() = FavoriteEntity(id,title,year, rating, imageUrl, description)

      override  fun getId() = id

        override fun showDetails(
            titleView: TextView,
            descriptionView: TextView,
            yearView: TextView,
            ratingView: TextView,
            @StringRes ratingResId: Int,
            @StringRes yearResId:Int
        ) {
            titleView.text = title
            descriptionView.text = Html.fromHtml(description)
            yearView.text = resourceManager.getMessage(yearResId,year)
            ratingView.text = resourceManager.getMessage(ratingResId,rating.toString())

        }

        override fun show(textView: TextView) {
            textView.text = title
        }

        override fun showDescription(textView: TextView) {
            textView.text = description
        }

        override fun showImage(imageView: ImageView) {
            Glide.with(imageView.context).load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(imageView)
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