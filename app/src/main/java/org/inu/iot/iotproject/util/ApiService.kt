package org.inu.iot.iotproject.util

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.inu.iot.iotproject.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("member/signup")
    abstract fun signUp(
        @Body body : JsonObject): Call<JsonObject>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("member/signin")
    abstract fun signIn(
        @Body body : JsonObject): Call<SignInResponse>

    @GET("member/{id}")
    abstract fun getUserInfo(
        @Header("X-AUTH-TOKEN") userToken : String,
        @Path("id") id : Long
    ) : Call<sterilizersList>

//    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("sterilizer")
    abstract fun insertSterilizer(
        @Header("X-AUTH-TOKEN") userToken : String,
        @Body body : JsonObject): Call<ResponseInsertItem>

    @DELETE("sterilizer/{sterilizerId}")
    abstract fun deleteSterilizer(
        @Header("X-AUTH-TOKEN") userToken : String,
        @Path("sterilizerId") sterilizerId : Long)
            : Call<ResponseBody>

    @GET("sterilizers")
    abstract fun getSterilizers(
        @Header("X-AUTH-TOKEN") userToken : String
    ) : Call<sterilizersList>

    @GET("sterilizer/{sterilizerId}")
    abstract fun getSterilizer(
        @Header("X-AUTH-TOKEN") userToken : String,
        @Path("sterilizerId") sterilizerId : Long) : Call<SanitizerDetailModel>

    @GET("useHistory")
    abstract fun getUsedHistory(
        @Header("X-AUTH-TOKEN") userToken : String,
        @Query("sterilizerId") sterilizerId : Long) : Call<UseHistoryModel>

    @GET("changeHistory")
    abstract fun getChangedHistory(
        @Header("X-AUTH-TOKEN") userToken : String,
        @Query("sterilizerId") sterilizerId : Long) : Call<changeHistory>

}