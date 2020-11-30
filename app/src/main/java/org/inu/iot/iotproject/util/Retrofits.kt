package org.inu.iot.iotproject.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofits {

    fun getService() : ApiService = retrofit.create(ApiService::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl(Config().getServerURL())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}