package com.android.movieapp.data.cache

import androidx.room.*
import com.android.movieapp.data.cache.entity.FavoriteEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteMovie(movie: FavoriteEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie: FavoriteEntity)

    @Query("SELECT * FROM favorites WHERE id = :movieId")
    suspend fun getMovieById(movieId:Int): FavoriteEntity?

}