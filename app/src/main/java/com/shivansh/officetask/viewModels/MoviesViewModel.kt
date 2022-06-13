package com.shivansh.officetask.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivansh.officetask.retrofit.MoviesRepo
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel() : ViewModel() {
    val topRatedMovies = MutableLiveData<List<Results>>()
    val upcomingMovies = MutableLiveData<List<Results>>()
    val popularMovies = MutableLiveData<List<Results>>()
    val nowPlayingMovies = MutableLiveData<List<Results>>()

    init {
       getMovies()
    }
/*
getting Movies Data from repository
 */
    private fun getMovies(){
        CoroutineScope(Dispatchers.IO).launch {
            val topRatedMoviesList = MoviesRepo.getTopRatedMovies().body()?.results
                withContext(Dispatchers.Main){
                    topRatedMovies.value = topRatedMoviesList!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val upcomingMoviesList = MoviesRepo.getUpcomingMovies().body()?.results
                withContext(Dispatchers.Main){
                    upcomingMovies.value = upcomingMoviesList!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val popularMoviesList = MoviesRepo.getPopularMovies().body()?.results
            withContext(Dispatchers.Main){
                popularMovies.value = popularMoviesList!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val nowPlayingMoviesList = MoviesRepo.getNowPlayingMovies().body()?.results
            withContext(Dispatchers.Main){
                nowPlayingMovies.value = nowPlayingMoviesList!!
            }
        }
    }
}