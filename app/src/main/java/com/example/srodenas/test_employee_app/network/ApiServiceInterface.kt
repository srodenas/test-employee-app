package com.example.srodenas.test_employee_app.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceInterface {

    @POST("auth")
    suspend fun login(@Body log: RequestEmployee): Response<ResponseEmployee>

    @POST("register")
    suspend fun register(@Body log: RequestEmployee): Response<ResponseEmployee>


    @GET("employee")
    suspend fun getAll(@Header("Authorization") token: String): Response<List<ResponseEmployee>>

}