package com.example.lessonplanapp

import com.example.lessonplanapp.Posts.Comments.PostsComments
import com.example.lessonplanapp.Posts.Posts
import retrofit2.http.GET
import retrofit2.http.Path

interface LessonPlanApi {
        @GET("posts")
        suspend fun getPosts(): Posts

        @GET("posts/{id}/comments")
        suspend fun getPostComment(@Path("id") int: Any): PostsComments

        /*TODO: Waiting for api*/
//        @GET("workers-group/{departmentName}")
//        suspend fun getDepartmentName()
//
//        @GET("workers-group/{:}departmentName}/{workerName}")
//        suspend fun getWorkerName()
//
//        @GET("rooms")
//        suspend fun getRooms()
//
//        @GET("rooms/{buildingName}")
//        suspend fun getBuildingNames()
//
//        @GET("rooms/{buildingName}/{roomNumber}")
//        suspend fun getRoomNumber()
//
//        @GET("courses/:{departmentName}")
//        suspend fun getCourses()
//
//        @GET("courses/{departmentName}/{courseName}")
//        suspend fun getCoursesName()
}