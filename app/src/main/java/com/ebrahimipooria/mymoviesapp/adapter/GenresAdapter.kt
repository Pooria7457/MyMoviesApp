package com.ebrahimipooria.mymoviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.mymoviesapp.R

class GenresAdapter(private val genresName: List<String>,
                    private val genresId: ArrayList<Int>,
                    private val onGenreSelected: OnGenreSelected) :
    RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GenresViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_genres, viewGroup, false)
        return GenresViewHolder(view)
    }

    override fun onBindViewHolder(genresViewHolder: GenresViewHolder, i: Int) {
        val genreName = genresName[i]
        val genreId = genresId[i]
        genresViewHolder.txtTitle.text = genreName
        genresViewHolder.parent.setOnClickListener { onGenreSelected.onSelected(genreName, genreId) }
    }

    override fun getItemCount(): Int {
        return genresName.size
    }

    inner class GenresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView
        var parent: RelativeLayout

        init {
            txtTitle = itemView.findViewById<View>(R.id.txt_ItemGenres) as TextView
            parent = itemView.findViewById<View>(R.id.rel_ItemGenres_parent) as RelativeLayout
        }
    }

    interface OnGenreSelected {
        fun onSelected(genreName: String?,genreId: Int?)
    }
}

