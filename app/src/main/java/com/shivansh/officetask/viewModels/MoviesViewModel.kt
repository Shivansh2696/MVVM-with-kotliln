package com.shivansh.officetask.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivansh.officetask.retrofit.TmdbRepo
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MovieResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel() : ViewModel() {
    val topRatedMovies = MutableLiveData<List<MovieResults>>()
    val upcomingMovies = MutableLiveData<List<MovieResults>>()
    val popularMovies = MutableLiveData<List<MovieResults>>()
    val nowPlayingMovies = MutableLiveData<List<MovieResults>>()
    val latestMovies = MutableLiveData<List<MovieResults>>()


    init {
       getMovies()
    }
/*
getting Movies Data from repository
 */
    private fun getMovies(){
        CoroutineScope(Dispatchers.IO).launch {
            val topRatedMoviesList = TmdbRepo.getTopRatedMovies().body()?.results
                withContext(Dispatchers.Main){
                    topRatedMovies.value = topRatedMoviesList!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val upcomingMoviesList = TmdbRepo.getUpcomingMovies().body()?.results
                withContext(Dispatchers.Main){
                    upcomingMovies.value = upcomingMoviesList!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val popularMoviesList = TmdbRepo.getPopularMovies().body()?.results
            withContext(Dispatchers.Main){
                popularMovies.value = popularMoviesList!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val nowPlayingMoviesList = TmdbRepo.getNowPlayingMovies().body()?.results
            withContext(Dispatchers.Main){
                nowPlayingMovies.value = nowPlayingMoviesList!!
            }
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            val latestMoviesList =TmdbRepo.getLatestMovies().body()?.results
//            withContext(Dispatchers.Main){
//                latestMovies.value = latestMoviesList!!
//            }
//        }
    }
}