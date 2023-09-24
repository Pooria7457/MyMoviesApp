package com.ebrahimipooria.mymoviesapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebrahimipooria.mymoviesapp.api.ApiInterface
import com.ebrahimipooria.mymoviesapp.model.Model
import com.ebrahimipooria.mymoviesapp.adapter.MoviesAdapter
import com.ebrahimipooria.mymoviesapp.api.RetrofitClient
import com.ebrahimipooria.mymoviesapp.model.ResponseListMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var list: ArrayList<Model> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //از کلاسی که ساختیم retrofit رو فراخوانی میکنیم
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
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


    }

}






