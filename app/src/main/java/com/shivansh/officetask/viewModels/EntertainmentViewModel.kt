package com.shivansh.officetask.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivansh.officetask.retrofit.TmdbRepo
import com.shivansh.officetask.tmdbReponse.personResponse.PersonResults
import com.shivansh.officetask.tmdbReponse.tvShowResponse.TVShowResults
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MovieResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EntertainmentViewModel : ViewModel() {

    val movies = MutableLiveData<List<MovieResults>>()
    val webSeries = MutableLiveData<List<TVShowResults>>()
    val persons = MutableLiveData<List<PersonResults>>()

    var AllMovieResponse = MutableLiveData<Boolean>()
    var AllWebShowsResponse = MutableLiveData<Boolean>()
    var AllCelebrityResponse = MutableLiveData<Boolean>()

    init {
        getData()
        AllMovieResponse.value = false
        AllWebShowsResponse.value = false
        AllCelebrityResponse.value = false
    }

    fun seeAllMovies(){
        AllMovieResponse.value = true
    }

    fun seeAllWebShows(){
        AllWebShowsResponse.value = true
    }

    fun seeAllCelebrity(){
        AllCelebrityResponse.value = true
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val movieData = TmdbRepo.getPopularMovies().body()?.results
            withContext(Dispatchers.Main){
                movies.value = movieData!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val webSeriesData = TmdbRepo.getPopularTVShows().body()?.results
            withContext(Dispatchers.Main){
                webSeries.value = webSeriesData!!
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val personsData = TmdbRepo.getPopularPersons().body()?.results
            withContext(Dispatchers.Main){
                persons.value = personsData!!
            }
        }
    }
}