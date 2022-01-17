package com.reportit.reportit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    val instance:RestApi by lazy{
         val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.56.1:2022") // change this IP for testing by your actual machine IP
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(RestApi::class.java)
    }

}