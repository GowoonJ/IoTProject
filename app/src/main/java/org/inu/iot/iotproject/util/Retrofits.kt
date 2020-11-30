package org.inu.iot.iotproject.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofits {
    val SanitizerApi = Retrofit.Builder()
        .baseUrl(Config().getServerURL())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}