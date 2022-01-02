package com.reportit.reportit.Retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyService {
    @FormUrlEncoded
    @POST("saveinfo")

    fun storedata(
        @Field("location") location:String,
        @Field("description") description:String
    ):Observable<String>

}