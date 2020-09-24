package com.example.Android_WebService.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Android_WebService.model.Course
import com.example.Android_WebService.model.GeneralUserD
import com.example.Android_WebService.model.courseD
import com.example.Android_WebService.repository.CourseRepository
import org.json.JSONObject


class CourseViewModel : ViewModel() {

    private val repository = CourseRepository()
    //var courses = List<Course>()
    var coursesLiveData = MutableLiveData<List<Course>>()
    var studentsLiveData = MutableLiveData<courseD>()
    var infoStudentLiveData =  MutableLiveData<GeneralUserD>()

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

    fun getStudents(user: String,token: String, id:String){
        repository.getStud(user,token, id)
    }

    fun getStudentsData(){
        studentsLiveData = repository.getStudData()
    }

    fun addStudent (username: String, token: String, idCourse: String){
        repository.addStudent(username, token, idCourse)
    }

    fun getInfoStudent(user: String, idStud: String, token: String){
        repository.getInfoStudent(user, idStud, token)
    }

    fun getInfoStudentData(){
        infoStudentLiveData = repository.getInfoStudentData()
    }

}
