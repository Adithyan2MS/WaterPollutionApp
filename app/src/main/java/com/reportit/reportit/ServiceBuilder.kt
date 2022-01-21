package com.reportit.reportit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    val instance: RestApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:2001") // change this IP for testing by your actual machine IP
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(RestApi::class.java)
    }

}
