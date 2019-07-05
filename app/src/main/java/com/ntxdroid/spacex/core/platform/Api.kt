package com.ntxdroid.spacex.core.platform

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private const val endpoint = "https://api.spacexdata.com/v3/"
    private val converter = GsonConverterFactory.create()

    fun <T> service(clazz: Class<T>): T =
        Retrofit.Builder()
            .baseUrl(endpoint)
            .addConverterFactory(converter)
            .build().create(clazz)
}