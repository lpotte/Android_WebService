package com.example.Android_WebService.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Android_WebService.R
import com.example.Android_WebService.repository.api.Post
import kotlinx.android.synthetic.main.fragment_list.view.*

class Adapter(val posts: ArrayList<Post>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.title.text = post.title
            itemView.body.text = post.completed.toString()
        }
    }
}
