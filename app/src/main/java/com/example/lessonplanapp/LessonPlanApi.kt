package com.example.lessonplanapp

import com.example.lessonplanapp.Buildings.BuildingsData
import com.example.lessonplanapp.Courses.CoursesData
import com.example.lessonplanapp.Departments.DepartmentData
import com.example.lessonplanapp.Rooms.RoomNumbers.RoomNumbersData
import com.example.lessonplanapp.Rooms.RoomsData
import com.example.lessonplanapp.Specializations.SpecializationData
import com.example.lessonplanapp.Workers.WorkerGroup.WorkersGroupData
import com.example.lessonplanapp.Workers.WorkerName.WorkersNameData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LessonPlanApi {

        @GET("workers-group")
        suspend fun getWorkerGroups(): WorkersGroupData

        @GET("workers-group/{departmentName}")
        suspend fun getDepartmentName(@Path("departmentName") string: String, @Query("sort") sort: String ): DepartmentData

        @GET("workers-group/{departmentName}/{workerName}")
        suspend fun getWorkerName(@Path("departmentName") departmentName: String,@Path("workerName") string: String): WorkersNameData

        @GET("rooms")
        suspend fun getRooms(): BuildingsData

        @GET("rooms/{buildingName}")
        suspend fun getBuildingNames(@Path("buildingName") string: String): RoomsData

        @GET("rooms/{buildingName}/{roomNumber}")
        suspend fun getRoomNumber(@Path("buildingName") buildingName: String,@Path("roomNumber") roomNumber: String): RoomNumbersData

        @GET("courses/{departmentName}")
        suspend fun getCourses(@Path("departmentName") departmentName: String): CoursesData

        @GET("courses/{departmentName}/{courseName}")
        suspend fun getCoursesName(@Path("departmentName") departmentName: String,@Path("courseName") courseName: String): SpecializationData
}