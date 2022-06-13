package com.sunny.kotlinpractice.retrofit

import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey : String) : Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

}