package com.shivansh.officetask.repositories

import com.shivansh.officetask.utils.AppConstants.TMDB_KEY
import com.sunny.kotlinpractice.retrofit.NetworkRetrofit
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MoviesResponse
import retrofit2.Call
import retrofit2.Response

class MoviesFragmentRepo {

    private val instance = NetworkRetrofit.instance

    suspend fun getTopRatedMovies() : Response<MoviesResponse> {
        return instance.getMoviesApi().getTopRatedMovies(TMDB_KEY)
    }

    suspend fun getUpcomingMovies() : Response<MoviesResponse>{
        return instance.getMoviesApi().getUpcomingMovies(TMDB_KEY)
    }

    suspend fun getPopularMovies() : Response<MoviesResponse>{
        return instance.getMoviesApi().getPopularMovies(TMDB_KEY)
    }

    suspend fun getNowPlayingMovies() : Response<MoviesResponse>{
        return instance.getMoviesApi().getNowPlayingMovies(TMDB_KEY)
    }
}