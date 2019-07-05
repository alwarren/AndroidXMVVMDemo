package com.ntxdroid.spacex.domain.entity.info

import com.google.gson.annotations.SerializedName

data class Links(
    val website: String,
    val flickr: String,
    val twitter: String,
    @SerializedName("elon_twitter")
    val elonTwitter: String
)