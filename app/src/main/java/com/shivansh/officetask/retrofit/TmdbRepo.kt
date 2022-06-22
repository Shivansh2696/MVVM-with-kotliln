package com.shivansh.officetask.retrofit

import com.shivansh.officetask.tmdbReponse.personResponse.PersonResponse
import com.shivansh.officetask.tmdbReponse.tvShowResponse.TVShowResponse
import com.shivansh.officetask.utils.AppConstants.TMDB_KEY
import com.sunny.kotlinpractice.retrofit.NetworkRetrofit
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MoviesResponse
import retrofit2.Response

object TmdbRepo {

    private val instance = NetworkRetrofit.instance

    suspend fun getTopRatedMovies() : Response<MoviesResponse> {
        return instance.getApi().getTopRatedMovies(TMDB_KEY)
    }

    suspend fun getUpcomingMovies() : Response<MoviesResponse> {
        return instance.getApi().getUpcomingMovies(TMDB_KEY)
    }

    suspend fun getPopularMovies() : Response<MoviesResponse> {
        return instance.getApi().getPopularMovies(TMDB_KEY)
    }

    suspend fun getNowPlayingMovies() : Response<MoviesResponse> {
        return instance.getApi().getNowPlayingMovies(TMDB_KEY)
    }

    suspend fun getLatestMovies() : Response<MoviesResponse>{
        return instance.getApi().getLatestMovies(TMDB_KEY)
    }

    suspend fun getTopRatedTVShows() : Response<TVShowResponse>{
        return instance.getApi().getTopRatedTVShows(TMDB_KEY)
    }

    suspend fun getPopularTVShows() : Response<TVShowResponse>{
        return instance.getApi().getPopularTVShows(TMDB_KEY)
    }

    suspend fun getLatestTVShows() : Response<TVShowResponse>{
        return instance.getApi().getLatestTVShows(TMDB_KEY)
    }

    suspend fun getPopularPersons() : Response<PersonResponse>{
        return instance.getApi().getPopularPersons(TMDB_KEY)
    }

    suspend fun getLatestPersons() : Response<PersonResponse>{
        return instance.getApi().getLatestPersons(TMDB_KEY)
    }
}