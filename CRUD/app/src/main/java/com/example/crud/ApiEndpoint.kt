package com.example.crud

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpoint {
    @GET("data.php")
    fun data() : Call<DataModel>

    @FormUrlEncoded
    @POST("create.php")
    fun create(
        @Field("nama") nama : String
    ) : Call<SimpanModel>

    @FormUrlEncoded
    @POST("update.php")
    fun update(
        @Field("id") id : String,
        @Field("nama") nama : String
    ) : Call<SimpanModel>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(
        @Field("id") id : String,
    ) : Call<SimpanModel>

}