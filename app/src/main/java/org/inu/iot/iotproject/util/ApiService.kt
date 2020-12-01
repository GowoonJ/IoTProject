package org.inu.iot.iotproject.util

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.inu.iot.iotproject.model.SignInResponse
import org.inu.iot.iotproject.model.sterilizersList
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
        @Field("password") password: String): Call<SignInResponse>

    @FormUrlEncoded
    @POST("sterilizer")
    abstract fun insertSterilizer(
        @Field("city") city: String,
        @Field("code") code: String,
        @Field("position") position: String,
        @Field("serialNumber") serialNumber: String,
        @Field("street") street: String)
            : Call<JsonObject>

    @DELETE("sterilizer/{sterilizerId}")
    abstract fun deleteSterilizer(
        @Header("X-AUTH-TOKEN") userToken : String,
        @Path("sterilizerId") sterilizerId : Long)
            : Call<ResponseBody>

    @GET("sterilizers")
    abstract fun getSterilizers(
        @Header("X-AUTH-TOKEN") userToken : String
    ) : Call<sterilizersList>
}