package com.ebrahimipooria.mymoviesapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var moviesAdapter: MoviesAdapter
    var dataList : List<DataModel> = ArrayList()
    var list : List<Model> = ArrayList()
    lateinit var request : ApiInterface
    var url : String = "https://moviesapi.ir/api/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var apiClient = ApiClient()
        request = apiClient.getApiClient(url)!!.create(ApiInterface::class.java)

        recyclerView = findViewById(R.id.rv_Main_RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        request.getData().enqueue(object : Callback <DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {

                list = response.body()!!
                moviesAdapter = MoviesAdapter(applicationContext, list)
                recyclerView.adapter = moviesAdapter

                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Error", "My Error Is : "+t.message)
            }
        })


    }
}






