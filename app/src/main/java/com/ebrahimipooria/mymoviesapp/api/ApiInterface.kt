package com.ebrahimipooria.mymoviesapp.api

import com.ebrahimipooria.mymoviesapp.model.ResponseListMovies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/v1/movies?page=1")
    fun getAllMovies() : Call<ResponseListMovies>


}


