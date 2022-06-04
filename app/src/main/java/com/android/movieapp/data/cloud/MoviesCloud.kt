package com.android.movieapp.data.cloud


import com.android.movieapp.data.MoviesData
import com.google.gson.annotations.SerializedName


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

        class Base:Mapper<MoviesData>{
            override fun map(list: ArrayList<Film>): MoviesData {
                val result = list.toList()
                return MoviesData.Base(result)
            }
        }
    }

}



data class Film(
    @SerializedName("id")
     val id: Int,
    @SerializedName("localized_name")
     val localizedName: String,
    @SerializedName("name")
     val name: String?,
    @SerializedName("year")
     val year: Int,
    @SerializedName("rating")
     val rating: Double,
    @SerializedName("image_url")
     val imageUrl: String,
    @SerializedName("description")
     val description: String,
    @SerializedName("genres")
     val genres: ArrayList<String> = arrayListOf()
)



