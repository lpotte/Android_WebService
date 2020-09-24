package com.example.Android_WebService.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Android_WebService.R
import com.example.Android_WebService.model.GeneralUser
import com.example.Android_WebService.model.User
import com.example.Android_WebService.viewmodel.CourseViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_course.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class Course : Fragment(), OnUserClickListener {

    private var adapter = AdapterStudent(ArrayList(), this)
    var token = ""
    var username = ""
    var courseid = ""
    val courseViewModel: CourseViewModel by viewModels()

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
        welcome()
    }

    override fun onItemCLick(user: GeneralUser, position: Int) {
        Toast.makeText(context, "Test", Toast.LENGTH_LONG)
            .show()
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
                })

            }else {
                Toast.makeText(context, "Token failure ", Toast.LENGTH_LONG)
                    .show()
            }
    }


}