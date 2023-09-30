package com.ebrahimipooria.mymoviesapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ebrahimipooria.mymoviesapp.MyDatabase
import com.ebrahimipooria.mymoviesapp.R
import com.ebrahimipooria.mymoviesapp.api.ApiInterface
import com.ebrahimipooria.mymoviesapp.api.RetrofitClient
import com.ebrahimipooria.mymoviesapp.model.Model
import com.ebrahimipooria.mymoviesapp.model.ResponseListMovies
import com.ebrahimipooria.mymoviesapp.model.SingleDataMovie
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {

    var myDatabase: MyDatabase? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        val txtTitle = findViewById<TextView>(R.id.txt_Detail_Title)
        val txtCountry = findViewById<TextView>(R.id.txt_Detail_Country)
        val txtYear = findViewById<TextView>(R.id.txt_Detail_Year)
        val txtImdbRate = findViewById<TextView>(R.id.txt_Detail_ImdbRate)
        val imgPoster = findViewById<ImageView>(R.id.img_Detail_poster)
        val btnFavorites = findViewById<Button>(R.id.btn_Detail_Favorites)

        val id : Int = intent.extras!!.getInt("id")
        val title : String? = intent.extras!!.getString("title")
        val poster : String? = intent.extras!!.getString("poster")
        val country : String? = intent.extras!!.getString("country")
        val year : String? = intent.extras!!.getString("year")
        val imdb_rating : String? = intent.extras!!.getString("imdb_rating")

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        myDatabase = MyDatabase(applicationContext)

        apiInterface.getSingleMovie(id).enqueue(object : Callback<SingleDataMovie>{
            override fun onResponse(
                call: Call<SingleDataMovie>,
                response: Response<SingleDataMovie>
            ) {
                Picasso.get().load(poster).into(imgPoster)
                txtTitle.setText(title)
                txtCountry.setText("Counry : " + country)
                txtYear.setText("Year : " + year)
                txtImdbRate.setText("Imdb Rating : " + imdb_rating)
            }

            override fun onFailure(call: Call<SingleDataMovie>, t: Throwable) {
                Toast.makeText(this@DetailActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Log", "My Error Is : " + t.message)
            }
        })

        btnFavorites.setOnClickListener {
            myDatabase!!.addInfo(id)
            Toast.makeText(this@DetailActivity,"Success",Toast.LENGTH_SHORT).show()
        }

    }



}


