package com.shivansh.officetask.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivansh.officetask.R
import com.shivansh.officetask.utils.AppConstants
import com.shivansh.officetask.views.activities.MovieFullDetailActivity
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.MovieResults

class MovieFullDetailAdapter(private val context: Context?) : RecyclerView.Adapter<MoviesVH>() {

    private var list = mutableListOf<MovieResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_full_detail_card,parent,false)
        return MoviesVH(view)
    }

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        holder.onBind(list[position],context)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieResults>?){
        if(list != null){
            this.list = list.toMutableList()
            this.notifyDataSetChanged()
        }
    }
}

class MoviesVH(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val movieName : TextView = itemView.findViewById(R.id.movieName)
    private val movieRating : TextView = itemView.findViewById(R.id.movieRating)
    private val releaseDate : TextView = itemView.findViewById(R.id.movieReleaseDate)
    private val movieLanguage : TextView = itemView.findViewById(R.id.movieLanguage)
    private val movieImage : ImageView = itemView.findViewById(R.id.moviePoster)
    private val movieVote : TextView = itemView.findViewById(R.id.movieVote)
    private val movieCard : CardView = itemView.findViewById(R.id.movieCard)

    fun onBind(result: MovieResults, context: Context?){
        val image = AppConstants.IMAGE_BASE_URL +result.posterPath
        if (context != null) {
            Glide.with(context).load(image).into(movieImage)
        }

        ("Name :" + result.originalTitle).also { movieName.text = it }
        ("Rating :" + result.voteAverage.toString()).also { movieRating.text = it }
        ("Release Date :" + result.releaseDate).also { releaseDate.text = it }
        ("Language :" + result.language).also { movieLanguage.text = it }
        ("Vote :" + result.voteCount.toString()).also { movieVote.text = it }

        movieCard.setOnClickListener{
            val intent = Intent(context,MovieFullDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("movieDetail",result)
            intent.putExtras(bundle)
            context?.startActivity(intent)
        }
    }
}