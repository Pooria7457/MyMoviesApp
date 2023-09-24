package com.ebrahimipooria.mymoviesapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ebrahimipooria.mymoviesapp.R
import com.ebrahimipooria.mymoviesapp.model.Model
import com.ebrahimipooria.mymoviesapp.ui.DetailActivity
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
        holder.txt_ItemMovies_Title.setText(data[position].title)
        holder.txt_ItemMovies_Country.setText("Country : " + data[position].country)
        holder.txt_ItemMovies_Year.setText("Year : " + data[position].year)
        Picasso.get().load(data[position].poster).into(holder.img_ItemMovies_Poster)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView),
        View.OnClickListener {

        var txt_ItemMovies_Title : TextView
        var txt_ItemMovies_Country : TextView
        var txt_ItemMovies_Year : TextView
        var img_ItemMovies_Poster : ImageView

        init {

            txt_ItemMovies_Title = itemView.findViewById(R.id.txt_ItemMovies_Title)
            txt_ItemMovies_Country = itemView.findViewById(R.id.txt_ItemMovies_Country)
            txt_ItemMovies_Year = itemView.findViewById(R.id.txt_ItemMovies_Year)
            img_ItemMovies_Poster = itemView.findViewById(R.id.img_ItemMovies_Poster)

            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", data[adapterPosition].id)
            intent.putExtra("title", data[adapterPosition].title)
            intent.putExtra("poster", data[adapterPosition].poster)
            intent.putExtra("images", data[adapterPosition].images)
            intent.putExtra("genres", data[adapterPosition].genres)
            intent.putExtra("country", data[adapterPosition].country)
            intent.putExtra("year", data[adapterPosition].year)
            intent.putExtra("imdb_rating", data[adapterPosition].imdb_rating)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}