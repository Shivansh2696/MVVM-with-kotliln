package com.shivansh.officetask.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivansh.officetask.R
import com.shivansh.officetask.tmdbReponse.tvShowResponse.TVShowResults
import com.shivansh.officetask.utils.AppConstants.IMAGE_BASE_URL

class TvShowAdapter(private val context: Context?) : RecyclerView.Adapter<TvShowViewHolder>() {
    private var list = mutableListOf<TVShowResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tv_show_recycler_card,parent,false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.onBind(list[position],context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<TVShowResults>?){
        if(list != null){
            this.list = list.toMutableList()
            this.notifyDataSetChanged()
        }
    }
}

class TvShowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val showName: TextView = itemView.findViewById(R.id.tvShowName)
    private val showRating: TextView = itemView.findViewById(R.id.tvShowRating)
    private val releaseDate: TextView = itemView.findViewById(R.id.tvShowReleaseDate)
    private val showImage: ImageView = itemView.findViewById(R.id.tvShowPoster)

    fun onBind(result: TVShowResults,context: Context?){
        val image = IMAGE_BASE_URL+result.posterPath
        if (context != null){
            Glide.with(context).load(image).into(showImage)
        }

        showName.text = result.originalName
        showRating.text = result.popularity.toString()
        releaseDate.text = result.firstAirDate
    }
}