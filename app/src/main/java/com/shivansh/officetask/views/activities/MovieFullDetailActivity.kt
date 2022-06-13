package com.shivansh.officetask.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shivansh.officetask.R
import com.shivansh.officetask.databinding.ActivityMovieFullDetailBinding
import com.shivansh.officetask.utils.AppConstants
import com.shivansh.officetask.utils.AppConstants.IMAGE_BASE_URL
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.Results

class MovieFullDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieFullDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_full_detail)

        val movie : Results = intent.extras?.getSerializable("movieDetail") as Results

        val movieBackground = IMAGE_BASE_URL +movie.backdrop_path
        Glide.with(this).load(movieBackground).into(binding.backGroundImage)

        val moviePoster = IMAGE_BASE_URL +movie.posterPath
        Glide.with(this).load(moviePoster).into(binding.moviePoster)

        binding.movieName.text = movie.title
        binding.movieLanguage.text = movie.language
        binding.moviePopularity.text = movie.popularity.toString()
        binding.movieReleaseDate.text = movie.releaseDate
        binding.movieOverView.text = movie.overView
    }
}