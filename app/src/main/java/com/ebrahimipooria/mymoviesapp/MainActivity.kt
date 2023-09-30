package com.ebrahimipooria.mymoviesapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import com.ebrahimipooria.mymoviesapp.ui.NewFilmActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface
    var list: ArrayList<Model> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var txtGenre: TextView
    lateinit var btnSave: Button
    lateinit var btnFavorites: Button
    var myDatabase: MyDatabase? = null
    lateinit var filmId : ArrayList<Int>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        var edtSearch = findViewById<EditText>(R.id.edt_Main_Search)
        var imgSearch = findViewById<ImageView>(R.id.img_Main_Search)
        txtGenre = findViewById<TextView>(R.id.txt_Main_Genre)
        btnSave = findViewById<Button>(R.id.btn_Main_SaveNewFim)
        btnFavorites = findViewById<Button>(R.id.btn_Main_Favorites)

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

        btnSave.setOnClickListener {
            val intent = Intent(this@MainActivity,NewFilmActivity::class.java)
            startActivity(intent)
        }

        btnFavorites.setOnClickListener {
            filmId = ArrayList<Int>()
            myDatabase = MyDatabase(applicationContext)
            val cursor: Cursor = myDatabase!!.getInfos
            if(myDatabase!=null) {
                while (cursor.moveToNext()) {
                    if (!cursor.isAfterLast) {
                        filmId.remove(cursor.getInt(1))
                        filmId.add(cursor.getInt(1))
                        apiInterface.getAllMovies().enqueue(object : Callback<ResponseListMovies> {

                            override fun onResponse(
                                call: Call<ResponseListMovies>,
                                response: Response<ResponseListMovies>
                            ) {
                                list.clear()
                                for (i in response.body()!!.data) {
                                    for (singleId in filmId) {
                                        if (i.id.equals(singleId)) {
                                            list.add(i)
                                        }
                                    }
                                }
                                moviesAdapter = MoviesAdapter(applicationContext, list)
                                recyclerView.adapter = moviesAdapter

                            }

                            override fun onFailure(call: Call<ResponseListMovies>, t: Throwable) {
                                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("Log", "My Error Is : " + t.message)
                            }
                        })
                    }
                }
            }

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
