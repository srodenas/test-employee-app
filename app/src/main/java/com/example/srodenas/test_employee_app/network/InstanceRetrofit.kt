package com.example.srodenas.test_employee_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object InstanceRetrofit {
    private const val URL_BASE = "http://192.168.0.145:8081"


    val retrofitService : ApiServiceInterface by lazy {
        getRetrofit().create(ApiServiceInterface::class.java)
    }



    private fun getRetrofit() : Retrofit = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}