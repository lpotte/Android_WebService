package com.example.Android_WebService.apiService.course

import com.example.Android_WebService.model.Course
import com.example.Android_WebService.model.courseD
import retrofit2.Call
import retrofit2.http.*

interface CourseApi {

    @GET("{dbId}/courses")
    fun getCourses(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<List<Course>>

    @GET("{dbId}/courses/{courseId}")
    fun showCourse(@Path("dbId") user: String,@Path("courseId") courseId: String, @Header("Authorization") header: String): Call<courseD>

    @POST("{dbId}/courses")
    fun addCourse(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<Course>


}