package com.mylab.weathermapproject.datamodel


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable


@Parcelize
data class Wind(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("speed")
    val speed: Double?
) : Parcelable