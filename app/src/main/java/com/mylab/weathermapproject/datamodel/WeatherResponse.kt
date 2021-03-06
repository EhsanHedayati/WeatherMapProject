package com.mylab.weathermapproject.datamodel


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable


@Parcelize
data class WeatherResponse(
        @SerializedName("base")
    val base: String?,
        @SerializedName("clouds")
    val clouds: Clouds?,
        @SerializedName("cod")
    val cod: Int?,
        @SerializedName("coord")
    val coord: Coord?,
        @SerializedName("dt")
    val dt: Int?,
        @SerializedName("id")
    val id: Int?,
        @SerializedName("main")
    val main: Main?,
        @SerializedName("name")
    val name: String?,
        @SerializedName("sys")
    val sys: Sys?,
        @SerializedName("timezone")
    val timezone: Int?,
        @SerializedName("visibility")
    val visibility: Int?,
        @SerializedName("weather")
    val weather: List<Weather>?,
        @SerializedName("wind")
    val wind: Wind?
) : Parcelable