package com.ntxdroid.spacex.feature.mission

import com.ntxdroid.spacex.domain.entity.mission.Mission
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * https://api.spacexdata.com/v3/missions
 * https://api.spacexdata.com/v3/missions/{{mission_id}}
 */
interface MissionService {
    @GET("missions")
    fun getAll(): Call<List<Mission>>

    @GET("missions/{missionId}")
    fun getDetails(@Path("missionId") id: String): Call<Mission>
}