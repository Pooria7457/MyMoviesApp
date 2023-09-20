package com.ebrahimipooria.mymoviesapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var list : List<Model> = ArrayList()
    public final lateinit var request : ApiInterface
    var url : String = "https://moviesapi.ir/api/v1/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var apiClient = ApiClient()
        request = apiClient.getApiClient(url)!!.create(ApiInterface::class.java)

        request.getData().enqueue(object : Callback<List<Model>> {

            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                list = response.body()!!
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.e("Error", "My Error Is:"+t.message )
            }

        })


    }
}


