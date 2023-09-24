package com.ebrahimipooria.mymoviesapp.model

import com.google.gson.annotations.SerializedName

data class SingleDataMovie(

    @SerializedName("id")
    var id : Int,

    @SerializedName("title")
    var title : String,

    @SerializedName("poster")
    var poster : String,

    @SerializedName("year")
    var year : String,

    @SerializedName("rated")
    var rated : String,

    @SerializedName("released")
    var released : String,

    @SerializedName("runtime")
    var runtime : String,

    @SerializedName("director")
    var director : String,

    @SerializedName("writer")
    var writer : String,

    @SerializedName("actors")
    var actors : String,

    @SerializedName("plot")
    var plot : String,

    @SerializedName("country")
    var country : String,

    @SerializedName("awards")
    var awards : String,

    @SerializedName("metascore")
    var metascore : String,

    @SerializedName("imdb_rating")
    var imdb_rating : String,

    @SerializedName("imdb_votes")
    var imdb_votes : String,

    @SerializedName("imdb_id")
    var imdb_id : String,

    @SerializedName("type")
    var type : String,

    @SerializedName("genres")
    var genres : ArrayList<String>,

    @SerializedName("images")
    var images : ArrayList<String>

)
