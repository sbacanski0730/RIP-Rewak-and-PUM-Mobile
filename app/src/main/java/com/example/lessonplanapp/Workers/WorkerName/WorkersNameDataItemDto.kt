package com.example.lessonplanapp.Workers.WorkerName


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Keep
data class WorkersNameDataItemDto(
    @SerializedName("timeStart")
    val timeStart: TimeStartDto,
    @SerializedName("timeEnd")
    val timeEnd: TimeEndDto,
    @SerializedName("profesor")
    val profesor: String,
    @SerializedName("room")
    val room: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("group")
    val group: String,
    var localDate: String,
    var localTime: String
) {
}