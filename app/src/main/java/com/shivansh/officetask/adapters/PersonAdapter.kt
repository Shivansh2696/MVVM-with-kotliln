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
import com.shivansh.officetask.tmdbReponse.personResponse.PersonResults
import com.shivansh.officetask.utils.AppConstants.IMAGE_BASE_URL

class PersonAdapter(private val context: Context?) : RecyclerView.Adapter<PersonViewHolder>() {

    private var list = mutableListOf<PersonResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_recycler_card, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.onBind(list[position], context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<PersonResults>?) {
        if (list != null) {
            this.list = list.toMutableList()
            this.notifyDataSetChanged()
        }
    }
}

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val personName : TextView = itemView.findViewById(R.id.personName)
    private val personRating : TextView = itemView.findViewById(R.id.personRating)
    private val personImage : ImageView = itemView.findViewById(R.id.personImage)

    fun onBind(result: PersonResults, context: Context?){
        val image = IMAGE_BASE_URL+result.profilePhoto
        if (context != null) {
            Glide.with(context).load(image).into(personImage)
        }

        personName.text = result.personName
        personRating.text = result.popularity.toString()
    }
}
