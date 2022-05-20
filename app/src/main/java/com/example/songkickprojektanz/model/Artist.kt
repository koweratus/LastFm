package com.example.songkickprojektanz.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class Artist(

    @SerializedName("name")
    val name: String,
    @SerializedName("listeners")
    val listeners:  String,
    @SerializedName("playcount")
    val playCount:  String,
    @SerializedName("mbid")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("streamable")
    val streamable: String,
    @SerializedName("image")
    val image: List<Image>

)
