package com.reportit.reportit

import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @POST("saveinfo")
    @FormUrlEncoded
    fun addUser(
        @Field("location") location:String,@Field("description") description:String
    ): Call<DataInfo>
}