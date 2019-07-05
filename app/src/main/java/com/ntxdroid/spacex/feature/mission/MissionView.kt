package com.ntxdroid.spacex.feature.mission

import android.os.Parcel
import android.os.Parcelable
import com.ntxdroid.spacex.core.platform.KParcelable
import com.ntxdroid.spacex.domain.entity.mission.Mission

/**
 * Created by Al Warren on 1/25/2019.
 */

data class MissionView(
    val missionId: String,
    val missionName: String,
    val manufacturers: List<String>,
    val payloadIds: List<String>,
    val wikipedia: String,
    val website: String,
    val twitter: String,
    val description: String
) : KParcelable {

    constructor(mission: Mission) : this(
        mission.missionId,
        mission.missionName,
        mission.manufacturers,
        mission.payloadIds,
        mission.wikipedia,
        mission.website,
        mission.twitter ?: "",
        mission.description
    )

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() as List<String>,
        parcel.createStringArrayList() as List<String>,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
        )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(missionId)
            writeString(missionName)
            writeStringList(manufacturers)
            writeStringList(payloadIds)
            writeString(wikipedia)
            writeString(website)
            writeString(twitter)
            writeString(description)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MissionView> {
        override fun createFromParcel(parcel: Parcel): MissionView {
            return MissionView(parcel)
        }

        override fun newArray(size: Int): Array<MissionView?> {
            return arrayOfNulls(size)
        }
    }
}