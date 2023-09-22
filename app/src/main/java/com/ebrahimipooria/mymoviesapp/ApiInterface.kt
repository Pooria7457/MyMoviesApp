package com.ebrahimipooria.mymoviesapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("movies?page=1")
    fun getData() : Call<Model>



}


