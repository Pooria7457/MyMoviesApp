package com.ebrahimipooria.mymoviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ebrahimipooria.mymoviesapp.R
import com.ebrahimipooria.mymoviesapp.model.Model
import com.squareup.picasso.Picasso

class MoviesAdapter(var context: Context, data: List<Model>) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    var data: List<Model>

    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movies, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_Main_Title.setText(data[position].title)
        Picasso.get().load(data[position].poster).into(holder.img_Main_Poster)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView),
        View.OnClickListener {

        var txt_Main_Title : TextView
        var img_Main_Poster : ImageView

        init {

            txt_Main_Title = itemView.findViewById(R.id.txt_Main_Title)
            img_Main_Poster = itemView.findViewById(R.id.img_Main_Poster)

        }

        override fun onClick(view: View) {

        }
    }
}