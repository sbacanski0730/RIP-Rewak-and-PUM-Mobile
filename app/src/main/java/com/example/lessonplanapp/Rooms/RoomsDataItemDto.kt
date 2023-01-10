package com.example.lessonplanapp.Rooms


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RoomsDataItemDto(
    @SerializedName("name")
    val name: String
)