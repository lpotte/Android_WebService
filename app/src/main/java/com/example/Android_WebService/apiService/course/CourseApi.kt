package com.example.Android_WebService.apiService.course

import com.example.Android_WebService.model.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface CourseApi {

    @GET("{dbId}/courses")
    fun getCourses(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<List<Course>>

    @GET("{dbId}/courses/{courseId}")
    fun showCourse(@Path("dbId") user: String,@Path("courseId") courseId: String, @Header("Authorization") header: String): Call<courseD>

    @POST("{dbId}/courses")
    fun addCourse(@Path("dbId") user: String, @Header ("Authorization") header: String): Call<Course>

    @FormUrlEncoded
    @POST("{dbId}/students")
    fun addStudent(@Path("dbId") user: String, @Header ("Authorization") header: String, @Field("courseId") courseId: String ): Call<studentResponse>

    @GET("{dbId}/students/{studentId}")
    fun viewStudent(@Path("dbId") user: String,@Path("studentId") studentId: String, @Header("Authorization") header: String): Call<GeneralUserD>

}