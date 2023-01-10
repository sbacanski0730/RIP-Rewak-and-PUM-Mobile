package com.example.lessonplanapp.Specializations


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.time.LocalDate
import java.time.LocalTime

@Keep
data class SpecializationDataItemDto(
    @SerializedName("timeStart")
    val timeStart: TimeStartDto,
    @SerializedName("timeEnd")
    val timeEnd: TimeEndDto,
    @SerializedName("room")
    val room: String,
    @SerializedName("profesor")
    val profesor: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("group")
    val group: String,
    var localDate: String,
    var localTime: String
)