package com.ebrahimipooria.mymoviesapp.api

import com.ebrahimipooria.mymoviesapp.model.ResponseListMovies
import com.ebrahimipooria.mymoviesapp.model.SingleDataMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/v1/movies?page=1")
    fun getAllMovies() : Call<ResponseListMovies>

    @GET("/api/v1/movies/{id}")
    fun getSingleMovie(@Path("id") id: Int): Call<SingleDataMovie>

    @GET("/api/v1/movies?q=&page=")
    fun searchMovie(@Query("page") page: Int, @Query("q") name: String?): Call<ResponseListMovies>


}


