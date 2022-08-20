package com.android.movieapp.data.cloud


import com.android.movieapp.core.Movie
import com.android.movieapp.core.MovieMapper
import com.android.movieapp.data.MoviesData
import com.google.gson.annotations.SerializedName
import javax.inject.Inject



data class Film(
    @SerializedName("id")
     val id: Int,
    @SerializedName("name")
     val name: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("image")
     val imageUrl: Image,
    @SerializedName("summary")
    val description: String?
): Movie{
    override fun <T> map(mapper: MovieMapper<T>): T {
        return mapper.map(id,name,description?: "",imageUrl.medium,weight,rating.average)
    }
}


data class Rating(
    val average: Double
)

data class Image(
    val medium: String,
    val original: String
)



