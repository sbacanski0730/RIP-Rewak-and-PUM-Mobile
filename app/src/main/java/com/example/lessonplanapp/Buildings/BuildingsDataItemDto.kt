package com.example.lessonplanapp.Buildings


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class BuildingsDataItemDto(
    @SerializedName("name")
    val name: String
)