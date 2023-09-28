package com.ebrahimipooria.mymoviesapp.api

import com.ebrahimipooria.mymoviesapp.model.GenresMoviesData
import com.ebrahimipooria.mymoviesapp.model.NewFilmModel
import com.ebrahimipooria.mymoviesapp.model.ResponseListMovies
import com.ebrahimipooria.mymoviesapp.model.SingleDataMovie
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("/api/v1/movies?page=1")
    fun getAllMovies() : Call<ResponseListMovies>

    @GET("/api/v1/movies/{id}")
    fun getSingleMovie(@Path("id") id: Int): Call<SingleDataMovie>

    @GET("/api/v1/movies?q=&page=")
    fun searchMovie(@Query("page") page: Int, @Query("q") name: String?): Call<ResponseListMovies>

    @GET("/api/v1/genres")
    fun genresMovies(): Call<ArrayList<GenresMoviesData>>

    @GET("/api/v1/genres/{genre_id}/movies?page=")
    fun chooseGenresMovie(
        @Path("genre_id") genre: Int?,
        @Query("page") page: Int
    ): Call<ResponseListMovies>

    @POST("/api/v1/movies")
    fun newFilm(
        @Body newFilmModel: NewFilmModel
    ): Call<ResponseListMovies>


}


