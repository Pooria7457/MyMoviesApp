package com.ebrahimipooria.mymoviesapp.model

import com.google.gson.annotations.SerializedName

data class GenresMoviesData(

    @SerializedName("id")
    var id : Int ,

    @SerializedName("name")
    var name : String

    )
