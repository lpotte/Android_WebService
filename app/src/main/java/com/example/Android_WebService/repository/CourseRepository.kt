package com.example.Android_WebService.repository

import com.example.Android_WebService.apiService.course.CourseApiService
import org.json.JSONObject

class CourseRepository {

    private val service = CourseApiService()

    fun getCourses(user: String, token: String) = service.getCourses(user,token)

    fun addCourse(user: String, token: String) = service.addCourse(user,token)

    fun getCourseData() = service.getCourseData()

    fun getStud(user: String,token: String, id: String) = service.getStud(user,token, id)

    fun getStudData() = service.getStudData()

    fun addStudent(username: String, token: String, idCourse: String) = service.addStudent(username, token, idCourse)

    fun getInfoStudent(user: String, idStud: String, token: String) = service.getInfoStudent(user, idStud, token)

    fun getInfoStudentData() = service.getInfoStudentData()

    fun reset(user: String, token: String) = service.reset(user, token)
}