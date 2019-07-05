package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LaunchFailureDetails {

    @SerializedName("time")
    @Expose
    var time: Int? = null
    @SerializedName("altitude")
    @Expose
    var altitude: Int? = null
    @SerializedName("reason")
    @Expose
    var reason: String? = null

}
