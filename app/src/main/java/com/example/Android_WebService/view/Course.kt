package com.example.Android_WebService.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.Android_WebService.R
import com.example.Android_WebService.model.GeneralUser
import kotlinx.android.synthetic.main.fragment_course.view.*


class Course : Fragment(), OnUserClickListener {

    private var adapter = AdapterStudent(ArrayList(), this)

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

    override fun onItemCLick(user: GeneralUser, position: Int) {
        TODO("Not yet implemented")
    }


}