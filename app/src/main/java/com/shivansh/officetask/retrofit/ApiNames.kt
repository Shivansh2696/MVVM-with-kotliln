package com.sunny.kotlinpractice.retrofit

import com.shivansh.officetask.tmdbReponse.personResponse.PersonResponse
import com.shivansh.officetask.tmdbReponse.tvShowResponse.TVShowResponse
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNames {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey : String) : Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("movie/latest")
    suspend fun getLatestMovies(@Query("api_key") apiKey: String) : Response<MoviesResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(@Query("api_key") apiKey: String) : Response<TVShowResponse>

    @GET("tv/popular")
    suspend fun getPopularTVShows(@Query("api_key") apiKey: String) : Response<TVShowResponse>

    @GET("tv/latest")
    suspend fun getLatestTVShows(@Query("api_key") apiKey: String) : Response<TVShowResponse>

    @GET("person/popular")
    suspend fun getPopularPersons(@Query("api_key") apiKey: String) : Response<PersonResponse>

    @GET("person/latest")
    suspend fun getLatestPersons(@Query("api_key") apiKey: String) : Response<PersonResponse>

}