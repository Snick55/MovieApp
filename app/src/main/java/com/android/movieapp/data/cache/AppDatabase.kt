package com.android.movieapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.movieapp.data.cache.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class],version = 1)
abstract class  AppDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}