package com.example.Android_WebService.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Android_WebService.R
import com.example.Android_WebService.model.GeneralUser
import com.example.Android_WebService.model.GeneralUserD
import com.example.Android_WebService.model.User
import com.example.Android_WebService.model.studentResponse
import com.example.Android_WebService.viewmodel.CourseViewModel
import com.example.Android_WebService.viewmodel.loginViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_course.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.userinfo_dialog.view.*
import org.json.JSONObject
import java.net.URLEncoder


class Course : Fragment(), OnUserClickListener {

    private var adapter = AdapterStudent(ArrayList(), this)
    var token = ""
    var username = ""
    var email = ""
    var password = ""
    var courseid = ""
    var nameCourse = ""
    var profesorName =""
    val courseViewModel: CourseViewModel by viewModels()
    val loginViewModel: loginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireView().estudiantes.adapter = adapter
        requireView().estudiantes.layoutManager = LinearLayoutManager(requireContext())

        token =  requireArguments().getString("token").toString()
        username = requireArguments().getString("user").toString()
        courseid = requireArguments().getString("courseId").toString()
        var email = requireArguments().getString("email").toString()
        var password = requireArguments().getString("pass").toString()
        welcome()
        view.findViewById<TextView>(R.id.courseName).setText(nameCourse)
        view.findViewById<TextView>(R.id.professorName).setText(profesorName)
        view.findViewById<FloatingActionButton>(R.id.addEstudiante).setOnClickListener {
            courseViewModel.addStudent(username, token, courseid)
            welcome()
        }
    }

    override fun onItemCLick(user: GeneralUser, position: Int) {
        Toast.makeText(context, "Test ", Toast.LENGTH_LONG)
            .show()
        val mDialogView = LayoutInflater.from(this.context).inflate(R.layout.userinfo_dialog, null)

        val mBuilder = AlertDialog.Builder(this.context)
            .setView(mDialogView)
            .setTitle("Student Info")

        val mAlertDialog = mBuilder.show()
        mDialogView.closeBtn.setOnClickListener {
            mAlertDialog.dismiss()
        }
        mDialogView.userName.text = viewStudentInfo(user.id).name
    }

    fun welcome(){
            if (token != "") {
                courseViewModel.getStudents(username, token, courseid)
                courseViewModel.getStudentsData()

                //using the CourseViewModel
               courseViewModel.studentsLiveData.observe(getViewLifecycleOwner(), Observer { it ->
                    Toast.makeText(context, "users  failure ", Toast.LENGTH_LONG)
                    adapter.students.clear()
                    adapter.students.addAll(it.students)
                    adapter.notifyDataSetChanged()
                    nameCourse = it.name
                    profesorName = it.professor.name
                })

            }else {
                Toast.makeText(context, "Token failure ", Toast.LENGTH_LONG)
                    .show()
                //newToken()
                //welcome()
            }
    }

    fun viewStudentInfo(studentid: String): GeneralUserD {
        //newToken()
        lateinit var studentDetails: GeneralUserD
        if (token != "") {
            Toast.makeText(context, " ", Toast.LENGTH_LONG)
            courseViewModel.getInfoStudent(username, studentid, token)
            courseViewModel.getInfoStudentData()

            //using the CourseViewModel
            courseViewModel.infoStudentLiveData.observe(getViewLifecycleOwner(), Observer { it ->
                studentDetails = it
            })

        }else {
            Toast.makeText(context, "Token failure ", Toast.LENGTH_LONG)
                .show()
            //newToken()
        }
        return studentDetails
    }

    fun newToken(){
        loginViewModel.signIn(email,password,username).observe(getViewLifecycleOwner(), Observer { user: User ->
            //Log.d("MyOut", "Fragment  signIn " + user + " error " + user.error)
            token = user.token
        })
    }

}