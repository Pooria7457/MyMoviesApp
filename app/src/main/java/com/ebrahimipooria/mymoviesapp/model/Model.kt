package com.ebrahimipooria.mymoviesapp.model

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("poster")
    var poster: String,

    @SerializedName("year")
    var year: String,

    @SerializedName("country")
    var country: String,

    @SerializedName("imdb_rating")
    var imdb_rating: String,

    @SerializedName("genres")
    var genres: ArrayList<String>,

    @SerializedName("images")
    var images: ArrayList<String>,
)

