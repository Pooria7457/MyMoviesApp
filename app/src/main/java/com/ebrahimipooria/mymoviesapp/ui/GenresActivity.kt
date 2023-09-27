package com.ebrahimipooria.mymoviesapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.mymoviesapp.R
import com.ebrahimipooria.mymoviesapp.adapter.GenresAdapter
import com.ebrahimipooria.mymoviesapp.adapter.GenresAdapter.OnGenreSelected
import com.ebrahimipooria.mymoviesapp.api.ApiInterface
import com.ebrahimipooria.mymoviesapp.api.RetrofitClient
import com.ebrahimipooria.mymoviesapp.model.GenresMoviesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresActivity() : AppCompatActivity() {


    lateinit var genreNames : ArrayList<String>
    lateinit var genresId : ArrayList<Int>
    lateinit var recyclerView : RecyclerView
    lateinit var genresAdapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)

        genreNames = ArrayList<String>()
        genresId = ArrayList<Int>()
        recyclerView = findViewById(R.id.rv_Genres_Recycler)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.genresMovies().enqueue(object : Callback<ArrayList<GenresMoviesData>>{
            override fun onResponse(
                call: Call<ArrayList<GenresMoviesData>>,
                response: Response<ArrayList<GenresMoviesData>>
            ) {
                for(i in response.body()!!){
                    var genreName= i.name
                    var genreId  = i.id
                    genreNames.add(genreName)
                    genresId.add(genreId)
                    genresAdapter = GenresAdapter(genreNames,genresId,object: OnGenreSelected{
                        override fun onSelected(genreName: String?, genreId: Int?) {
                            val intent = Intent()
                            intent.putExtra("genreName",genreName)
                            intent.putExtra("genreId",genreId)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    })
                    recyclerView.adapter = genresAdapter
                }
            }

            override fun onFailure(call: Call<ArrayList<GenresMoviesData>>, t: Throwable) {
                Toast.makeText(this@GenresActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Log", "My Error Is : " + t.message)
            }
        })

    }



}