package com.example.lessonplanapp.Rooms.RoomNumbers


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RoomNumbersDataItemDto(
    @SerializedName("timeStart")
    val timeStart: TimeStartDto,
    @SerializedName("timeEnd")
    val timeEnd: TimeEndDto,
    @SerializedName("profesor")
    val profesor: String,
    @SerializedName("subject")
    val subject: String,
    var localDate: String,
    var localTime: String
)