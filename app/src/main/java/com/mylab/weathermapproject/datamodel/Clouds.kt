package com.mylab.weathermapproject.datamodel


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable


@Parcelize
data class Clouds(
    @SerializedName("all")
    val all: Int?
) : Parcelable