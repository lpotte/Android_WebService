package com.example.Android_WebService.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Android_WebService.R
import com.example.Android_WebService.model.Course
import com.example.Android_WebService.model.GeneralUser
import kotlinx.android.synthetic.main.fragment_list.view.*

class Adapter(val courses: ArrayList<Course>, var clickListener: OnCourseClickListener): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initialize(courses[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var courseName = itemView.title
        var professorName = itemView.body

        fun initialize(item: Course, action: OnCourseClickListener){
            courseName.text = item.name
            professorName.text = item.professor

            itemView.setOnClickListener {
                action.onItemCLick(item, adapterPosition)
            }
        }
    }
}

class AdapterStudent(val students: ArrayList<GeneralUser>, var clickListener: OnUserClickListener): RecyclerView.Adapter<AdapterStudent.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return students.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initialize(students[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var studentName = itemView.title
        var studentEmail = itemView.body

        fun initialize(item: GeneralUser, action: OnUserClickListener){
            studentName.text = item.name
            studentEmail.text = item.email


            itemView.setOnClickListener {
                action.onItemCLick(item, adapterPosition)
            }
        }
    }

}

interface OnCourseClickListener {
    fun onItemCLick(course: Course, position: Int)
}

interface OnUserClickListener {
    fun onItemCLick(user: GeneralUser, position: Int)
}
