package com.ntxdroid.spacex.feature.launch

import android.os.Parcel
import android.os.Parcelable
import com.ntxdroid.spacex.core.platform.KParcelable
import com.ntxdroid.spacex.domain.entity.launch.Launch

data class LaunchView(
    val flightNumber: Int,
    val missionName: String,
    val details: String,
    val launchDateUnix: Int,
    val launchSite: String
) : KParcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    constructor(launch: Launch) : this(
        launch.flightNumber,
        launch.missionName,
        launch.details ?: "",
        launch.launchDateUnix,
        launch.launchSite.siteNameLong
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(flightNumber)
            writeString(missionName)
            writeString(details)
            writeInt(launchDateUnix)
            writeString(launchSite)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LaunchView> {
        override fun createFromParcel(parcel: Parcel): LaunchView {
            return LaunchView(parcel)
        }

        override fun newArray(size: Int): Array<LaunchView?> {
            return arrayOfNulls(size)
        }
    }

}