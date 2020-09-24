package com.example.Android_WebService.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Android_WebService.model.Course
import com.example.Android_WebService.repository.CourseRepository
import com.example.Android_WebService.repository.api.Post
import kotlinx.coroutines.launch


class CourseViewModel : ViewModel() {

    private val repository = CourseRepository()
    //var courses = List<Course>()
    var coursesLiveData = MutableLiveData<List<Course>>()

    //private val repository = CourseRepository()

    fun getCourses(user: String, token: String){
        Log.d("MyOut", "CourseViewModel getCourses with token  <" + token+">")
        repository.getCourses(user, token)
    }

    fun addCourse(user: String, token: String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
        repository.addCourse(user, token)
    }

    fun getCourseData() {
        coursesLiveData = repository.getCourseData()
    }
    /*
    fun getCourses(user: String, token: String){
        viewModelScope.launch {
            val course = repository.getCourses(user, token) as Course
            courses.add(course)
            coursesLiveData.postValue(courses)
        }
    }

    fun addCourse(user: String, token: String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
        repository.addCourse(user, token)
    }

    fun getCourseData() = repository.getCourseData()*/



}
