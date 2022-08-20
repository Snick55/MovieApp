package com.android.movieapp.di

import com.android.movieapp.core.MovieMapper
import com.android.movieapp.data.MoviesData
import com.android.movieapp.domain.MoviesDomain
import com.android.movieapp.presentation.MoviesUi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesMapperModule {


    @Binds
    abstract fun bindMoviesDataMapper(
        moviesData: MoviesData.Mapper.Base
    ): MoviesData.Mapper<MoviesDomain>



    @Binds
    abstract fun bindMovieMapper(
        movieMapper: MovieMapper.FilmToMoviesUiMapper
    ): MovieMapper<MoviesUi>


}