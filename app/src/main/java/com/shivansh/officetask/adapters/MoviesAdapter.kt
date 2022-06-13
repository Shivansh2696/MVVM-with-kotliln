package com.shivansh.officetask.adapters

import android.annotation.SuppressLint
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
import com.shivansh.officetask.utils.AppConstants.IMAGE_BASE_URL
import com.shivansh.officetask.views.activities.MovieFullDetailActivity
import com.shivansh.officetask.views.fragments.drawerNavigationFragments.MoviesFragment
import com.sunny.kotlinpractice.tmdbResponse.moviesResponse.Results

class MoviesAdapter(private val context: MoviesFragment) : RecyclerView.Adapter<MyViewHolder>() {

    private var list = mutableListOf<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_recycler_card,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position],context)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Results>?){
        if(list != null){
            this.list = list.toMutableList()
            this.notifyDataSetChanged()
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    private val movieName : TextView = itemView.findViewById(R.id.movieName)
    private val movieRating : TextView = itemView.findViewById(R.id.Rating)
    private val releaseDate : TextView = itemView.findViewById(R.id.releaseDate)
    private val movieImage : ImageView = itemView.findViewById(R.id.moviePoster)
    private val movieCard : CardView = itemView.findViewById(R.id.movieCard)

    fun onBind(result: Results, context: MoviesFragment) {
        val image = IMAGE_BASE_URL+result.posterPath
        Glide.with(context).load(image).into(movieImage)

        movieName.text = result.title
        movieRating.text = result.voteAverage.toString()
        releaseDate.text = result.releaseDate

        movieCard.setOnClickListener {
            val intent = Intent(context.activity, MovieFullDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("movieDetail",result)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}