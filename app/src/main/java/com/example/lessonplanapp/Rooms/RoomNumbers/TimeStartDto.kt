package com.example.lessonplanapp.Rooms.RoomNumbers


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TimeStartDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone_type")
    val timezoneType: Int,
    @SerializedName("timezone")
    val timezone: String
)