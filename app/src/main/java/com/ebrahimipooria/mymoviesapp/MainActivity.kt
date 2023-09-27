package com.ebrahimipooria.mymoviesapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.mymoviesapp.adapter.MoviesAdapter
import com.ebrahimipooria.mymoviesapp.api.ApiInterface
import com.ebrahimipooria.mymoviesapp.api.RetrofitClient
import com.ebrahimipooria.mymoviesapp.model.Model
import com.ebrahimipooria.mymoviesapp.model.ResponseListMovies
import com.ebrahimipooria.mymoviesapp.ui.GenresActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface
    var list: ArrayList<Model> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var txtGenre: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var edtSearch = findViewById<EditText>(R.id.edt_Main_Search)
        var imgSearch = findViewById<ImageView>(R.id.img_Main_Search)
        txtGenre = findViewById<TextView>(R.id.txt_Main_Genre)

        //از کلاسی که ساختیم retrofit رو فراخوانی میکنیم
        var retrofit = RetrofitClient.getInstance()
        apiInterface = retrofit.create(ApiInterface::class.java)
        recyclerView = findViewById(R.id.rv_Main_RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        //مدل درست رو بهش پاس میدیم
        apiInterface.getAllMovies().enqueue(object : Callback<ResponseListMovies> {

            override fun onResponse(
                call: Call<ResponseListMovies>,
                response: Response<ResponseListMovies>
            ) {
                //  هر ایتمی که در data موجوده داخل لیست میریزیم
                for(i in response.body()!!.data){
                    list.add(i)
                }
                moviesAdapter = MoviesAdapter(applicationContext, list)
                recyclerView.adapter = moviesAdapter

            }

            override fun onFailure(call: Call<ResponseListMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Log", "My Error Is : " + t.message)
            }
        })

        imgSearch.setOnClickListener {

            var nameText = edtSearch.text.toString()

            apiInterface.searchMovie(1,nameText).enqueue(
                object : Callback<ResponseListMovies>{
                    override fun onResponse(
                        call: Call<ResponseListMovies>,
                        response: Response<ResponseListMovies>
                    ) {

                        list.clear()

                            for (i in response.body()!!.data) {
                                list.add(i)
                            }


                        moviesAdapter = MoviesAdapter(applicationContext, list)
                        recyclerView.adapter = moviesAdapter
                    }

                    override fun onFailure(call: Call<ResponseListMovies>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.e("Log", "My Error Is : " + t.message)
                    }
                }
            )


        }



        txtGenre.setOnClickListener {
            var intent = Intent(this@MainActivity,GenresActivity::class.java)
            startActivityForResult(intent, 1001)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == RESULT_OK && data != null) {
            txtGenre.setText("Genre : "+data.extras!!.getString("genreName"))
            var id = data.extras!!.getInt("genreId")
            apiInterface.chooseGenresMovie(id,1).enqueue(
                object : Callback<ResponseListMovies>{
                    override fun onResponse(
                        call: Call<ResponseListMovies>,
                        response: Response<ResponseListMovies>
                    ) {
                        list.clear()

                        for (i in response.body()!!.data) {
                            list.add(i)
                        }


                        moviesAdapter = MoviesAdapter(applicationContext, list)
                        recyclerView.adapter = moviesAdapter
                    }

                    override fun onFailure(call: Call<ResponseListMovies>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                        Log.e("Log", "My Error Is : " + t.message)
                    }
                }
            )
        }
    }



}
