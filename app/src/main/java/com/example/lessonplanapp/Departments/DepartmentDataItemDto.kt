package com.example.lessonplanapp.Departments


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DepartmentDataItemDto(
    @SerializedName("name")
    val name: String
)