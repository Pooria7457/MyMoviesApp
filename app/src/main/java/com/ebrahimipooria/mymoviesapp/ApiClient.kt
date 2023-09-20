package com.ebrahimipooria.mymoviesapp

import retrofit2.converter.gson.GsonConverterFactory


import retrofit2.Retrofit

open class ApiClient {
    open var retrofit: Retrofit? = null

    open fun getApiClient (baseUrl:String): Retrofit? {
        if(retrofit==null){
            retrofit= Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}