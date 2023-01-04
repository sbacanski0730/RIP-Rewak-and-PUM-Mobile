package com.example.lessonplanapp.Posts.Comments
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PostsCommentsDto(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
)