package com.example.Android_WebService.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Android_WebService.R
import com.example.Android_WebService.model.Course
import com.example.Android_WebService.model.User
import com.example.Android_WebService.viewmodel.CourseViewModel
import com.example.Android_WebService.viewmodel.loginViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.collections.List

class Home : Fragment(), OnCourseClickListener {
    lateinit var navController: NavController
    val loginViewModel: loginViewModel by viewModels()
    val courseViewModel: CourseViewModel by viewModels()
    private var adapter = Adapter(ArrayList(), this)
    var theToken = ""
    var username = ""
    var password = ""
    var email = ""
    lateinit var usu: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // requireView gets the root view for the fragment's layout
        // (the one returned by onCreateView).
        requireView().posts_recycler.adapter = adapter
        requireView().posts_recycler.layoutManager = LinearLayoutManager(requireContext())

        // get the live data and start observing
      /*  postViewModel.postsLiveData.observe(viewLifecycleOwner, Observer { it ->
            adapter.posts.clear()
            adapter.posts.addAll(it)
            adapter.notifyDataSetChanged()
        })*/

        //using the CourseViewModel
        courseViewModel.coursesLiveData.observe(getViewLifecycleOwner(), Observer { it ->
            Toast.makeText(context, "Token cambió", Toast.LENGTH_LONG).show()
            adapter.courses.clear()
            adapter.courses.addAll(it)
            adapter.notifyDataSetChanged()
        })

        //token = requireArguments().getString("token").toString()
        username = requireArguments().getString("name").toString()
        password = requireArguments().getString("pass").toString()
        email = requireArguments().getString("email").toString()


        // iniciar sesión automaticamente
        welcome()

        //Floating Buttom
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            welcome()
        }

        /*view.findViewById<AppCompatImageView>(R.id.resetBtn).setOnClickListener {
            courseViewModel.reset(username, theToken)
        }*/

        //Navegación de sign out
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.sign_out).setOnClickListener {
            navController.navigate(R.id.action_home2_to_signIn)
        }

    }

    fun welcome(){
        loginViewModel.signIn(email,password,username).observe(getViewLifecycleOwner(), Observer { user: User ->
            //Log.d("MyOut", "Fragment  signIn " + user + " error " + user.error)
            theToken = user.token
            if (user.token != "") {
                courseViewModel.addCourse(username,theToken)
                courseViewModel.getCourses(username,theToken)
                courseViewModel.getCourseData()
                //using the CourseViewModel
                courseViewModel.coursesLiveData.observe(getViewLifecycleOwner(), Observer { it ->
                    adapter.courses.clear()
                    adapter.courses.addAll(it)
                    adapter.notifyDataSetChanged()
                })
            } else {
                Toast.makeText(context, "Token failure " + user.error, Toast.LENGTH_LONG)
                    .show()
            }
        })
        //postViewModel.getPost()
    }

    override fun onItemCLick(course: Course, position: Int) {
        Toast.makeText(this.context, "Professor " + course.professor, Toast.LENGTH_LONG).show()
        Log.d("Test", "Professor " + course.professor)
        var navController = findNavController()
        var bundle = bundleOf("user" to username, "courseId" to course.id, "token" to theToken, "email" to email, "pass" to password)
        navController.navigate(R.id.action_home2_to_course, bundle)
    }


}

