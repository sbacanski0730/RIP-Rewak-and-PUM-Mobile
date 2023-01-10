package com.example.lessonplanapp.Courses


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CoursesDataItemDto(
    @SerializedName("name")
    val name: String
)