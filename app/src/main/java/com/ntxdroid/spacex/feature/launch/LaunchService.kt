package com.ntxdroid.spacex.feature.launch

import com.ntxdroid.spacex.domain.entity.launch.Launch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * https://api.spacexdata.com/v3/launches/upcoming
 * https://api.spacexdata.com/v3/launches/{{flight_number}}
 */
interface LaunchService {
    @GET("launches/upcoming")
    fun getAll(): Call<List<Launch>>

    @GET("launches/{flightNumber}")
    fun getDetails(@Path("flightNumber") id: Int): Call<Launch>
}