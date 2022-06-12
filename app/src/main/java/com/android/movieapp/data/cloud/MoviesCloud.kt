package com.android.movieapp.data.cloud


import com.android.movieapp.data.MoviesData
import com.google.gson.annotations.SerializedName


interface MoviesCloud {

    fun test(): String
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("films")
        private val films: ArrayList<Film>
    ):MoviesCloud{
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(films)
       override fun test() = films[0].imageUrl

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
     val id: Int,
     @SerializedName("localized_name")
     val localizedName: String,
     val name: String,
     val year: Int,
     val rating: Double,
     @SerializedName("image_url")
     val imageUrl: String,
     val description: String,
     val genres: ArrayList<String> = arrayListOf()
)



