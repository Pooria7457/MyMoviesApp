package com.ebrahimipooria.mymoviesapp.model

import com.google.gson.annotations.SerializedName


data class NewFilmModel(

    @SerializedName("title")
    var title : String?,

    @SerializedName("imdb_id")
    var imdb_id : String?,

    @SerializedName("country")
    var country : String?,

    @SerializedName("year")
    var year : Int?,

    @SerializedName("imdb_rating")
    var imdb_rating : String?,

    @SerializedName("poster")
    var poster : String?

)
