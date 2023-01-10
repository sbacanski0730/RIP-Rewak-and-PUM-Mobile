package com.example.lessonplanapp.Workers.WorkerName


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.util.Date

@Keep
data class TimeStartDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone_type")
    val timezoneType: Int,
    @SerializedName("timezone")
    val timezone: String
)