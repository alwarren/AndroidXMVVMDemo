package com.ntxdroid.spacex.feature.launch

import android.os.Parcel
import android.os.Parcelable
import com.ntxdroid.spacex.core.platform.Converters
import com.ntxdroid.spacex.core.platform.KParcelable
import com.ntxdroid.spacex.domain.entity.launch.Launch

data class LaunchView(
    val flightNumber: Int,
    val missionName: String,
    val details: String,
    val launchDateUnix: Int,
    val launchDate: String,
    val launchSite: String
) : KParcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    constructor(launch: Launch) : this(
        launch.flightNumber,
        launch.missionName,
        launch.details ?: "",
        launch.launchDateUnix,
        Converters.dateString(launch.launchDateUnix.toLong()),
        launch.launchSite.siteNameLong
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(flightNumber)
            writeString(missionName)
            writeString(details)
            writeInt(flightNumber)
            writeString(launchDate)
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

        val mock = LaunchView(
            650,
            "Telstar 19V",
            "SSL-manufactured communications satellite intended to be placed at 63° West over the Americas. At 7,075 kg, it became the heaviest commercial communications satellite ever launched.",
            1532238600,
            "Sun, Jul 22, 2018 00:50 UTC",
            "Cape Canaveral Air Force Station Space Launch Complex 40"
        )
    }

}