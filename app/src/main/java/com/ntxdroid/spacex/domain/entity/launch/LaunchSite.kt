package com.ntxdroid.spacex.domain.entity.launch

import com.google.gson.annotations.SerializedName

data class LaunchSite(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("site_name")
    val siteName: String,
    @SerializedName("site_name_long")
    val siteNameLong: String
) {
    companion object {
        val empty = LaunchSite("", "", "")
        val mock = LaunchSite("ccafs_slc_40", "CCAFS SLC 40", "Cape Canaveral Air Force Station Space Launch Complex 40")
    }
}