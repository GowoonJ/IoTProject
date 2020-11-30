package org.inu.iot.iotproject.util

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("member/signup")
    abstract fun signUp(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String): Call<JsonObject>

    @FormUrlEncoded
    @POST("member/signin")
    abstract fun signIn(
        @Field("email") email: String,
        @Field("password") password: String): Call<JsonObject>
}