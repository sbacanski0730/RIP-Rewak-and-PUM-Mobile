package com.example.lessonplanapp.Posts


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PostsDto(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)