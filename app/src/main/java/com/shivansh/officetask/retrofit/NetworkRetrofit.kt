package com.sunny.kotlinpractice.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal open class NetworkRetrofit() {
    private var httpClient: OkHttpClient.Builder? = null
    private var moviesApi : MoviesApi

    companion object{
        val instance : NetworkRetrofit by lazy { NetworkRetrofit() }
    }

    init {
        httpClient = OkHttpClient.Builder()
        moviesApi = create("https://api.themoviedb.org/3/",MoviesApi::class.java)
    }

    fun getMoviesApi(): MoviesApi {
        return moviesApi
    }

    private fun <T> create(baseUrl : String, cls : Class<T>) : T{
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient!!.build())
            .build()
        return retrofit.create(cls)
    }
}