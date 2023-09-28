package com.ebrahimipooria.mymoviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ebrahimipooria.mymoviesapp.R
import com.ebrahimipooria.mymoviesapp.api.ApiInterface
import com.ebrahimipooria.mymoviesapp.api.RetrofitClient
import com.ebrahimipooria.mymoviesapp.model.NewFilmModel
import com.ebrahimipooria.mymoviesapp.model.ResponseListMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewFilmActivity : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface
    lateinit var newFilmModel: NewFilmModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_film)

        val edtTitle : EditText = findViewById(R.id.edt_NewFilm_Title)
        val edtImdbId : EditText = findViewById(R.id.edt_NewFilm_ImdbId)
        val edtCountry : EditText = findViewById(R.id.edt_NewFilm_Country)
        val edtYear : EditText = findViewById(R.id.edt_NewFilm_Year)
        val edtImdbRating : EditText = findViewById(R.id.edt_NewFilm_ImdbRating)
        val edtPoster : EditText = findViewById(R.id.edt_NewFilm_Poster)
        val btnSave : Button = findViewById(R.id.btn_NewFilm_Save)

        val title = edtTitle.text
        val imdbId = edtImdbId.text
        val country = edtCountry.text
        val year = edtYear.text
        val imdbRating = edtImdbRating.text
        val poster = edtPoster.text

        val retrofit = RetrofitClient.getInstance()
        apiInterface = retrofit.create(ApiInterface::class.java)

        btnSave.setOnClickListener {

            if (title.toString().isEmpty()||imdbId.toString().isEmpty()||
                country.toString().isEmpty()||year.toString().isEmpty()||
                imdbRating.toString().isEmpty()||poster.toString().isEmpty()
            ){

                Toast.makeText(this@NewFilmActivity,"Please insert all field",Toast.LENGTH_SHORT).show()

            }else{
                val myYear = year.toString()
                newFilmModel = NewFilmModel(title.toString(),imdbId.toString(),
                    country.toString(),myYear.toInt(),imdbRating.toString(),
                    poster.toString())

                apiInterface.newFilm(newFilmModel).enqueue(object :Callback<ResponseListMovies>{
                    override fun onResponse(
                        call: Call<ResponseListMovies>,
                        response: Response<ResponseListMovies>
                    ) {
                        Toast.makeText(this@NewFilmActivity,"Success",Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<ResponseListMovies>, t: Throwable) {
                        Toast.makeText(this@NewFilmActivity,"Error",Toast.LENGTH_SHORT).show()
                    }

                })


            }

        }



    }
}