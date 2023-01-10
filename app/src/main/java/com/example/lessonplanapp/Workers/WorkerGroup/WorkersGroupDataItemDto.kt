package com.example.lessonplanapp.Workers.WorkerGroup


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class WorkersGroupDataItemDto(
    @SerializedName("name")
    val name: String
)