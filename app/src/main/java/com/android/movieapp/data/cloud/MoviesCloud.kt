package com.android.movieapp.data.cloud


import androidx.room.PrimaryKey
import com.android.movieapp.core.Movie
import com.android.movieapp.core.MovieMapper
import com.android.movieapp.data.MoviesData
import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton


interface MoviesCloud {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("films")
        private val films: ArrayList<Film>
    ):MoviesCloud{
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(films)

    }

    interface Mapper<T> {

        fun map(list: ArrayList<Film>):T

        @Singleton
        class Base @Inject constructor():Mapper<MoviesData>{
            override fun map(list: ArrayList<Film>): MoviesData {
                val result = list.toList()
                return MoviesData.Base(result)
            }
        }
    }

}
data class Film(
     val id: Int,
     @SerializedName("localized_name")
     val localizedName: String,
     val name: String,
     val year: Int,
     val rating: Double,
     @SerializedName("image_url")
     val imageUrl: String?,
     val description: String?,
     val genres: ArrayList<String> = arrayListOf()
): Movie{
    override fun <T> map(mapper: MovieMapper<T>): T {
        return mapper.map(id,localizedName,description?: "",imageUrl?: "",year,rating)
    }
}



