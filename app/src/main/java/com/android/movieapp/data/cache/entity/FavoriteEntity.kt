package com.android.movieapp.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.movieapp.core.FavoriteMapper
import com.android.movieapp.core.MovieMapper
import com.android.movieapp.data.cloud.Film

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "localized_name")
    val localizedName: String,
    val year: Int,
    val rating: Double,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    val description: String?
): FavoriteMapper{
    override fun <T> map(mapper: MovieMapper<T>): T {
      return  mapper.map(id,localizedName,description?:"",imageUrl?:"",year, rating)
    }
}


fun Film.asFavoriteEntity(): FavoriteEntity = FavoriteEntity(
    this.id, this.name, this.weight, this.rating.average, this.imageUrl.original, this.description
)

