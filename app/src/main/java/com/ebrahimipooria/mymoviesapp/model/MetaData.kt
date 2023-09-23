package com.ebrahimipooria.mymoviesapp.model

import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("current_page")
    var current_page:String,

    @SerializedName("per_page")
    var per_page:Int,

    @SerializedName("page_count")
    var page_count:Int,

    @SerializedName("total_count")
    var total_count:Int
)
