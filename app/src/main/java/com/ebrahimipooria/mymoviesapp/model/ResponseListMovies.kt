package com.ebrahimipooria.mymoviesapp.model

import com.google.gson.annotations.SerializedName

data class ResponseListMovies(

    //حتما باید serializedName ها درست باشه و مدل مثل جواب json باشه
    @SerializedName("data")
    var data: List<Model>,

    @SerializedName("metadata")
    var metaData: MetaData,
)
