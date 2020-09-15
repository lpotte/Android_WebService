package com.example.Android_WebService.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Android_WebService.repository.PostRepository
import com.example.Android_WebService.repository.api.Post
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    private val repository = PostRepository()
    val posts = mutableListOf<Post>()
    val postsLiveData = MutableLiveData<List<Post>>()

    init {
        getPost()
    }

    private fun getPosts() {
        viewModelScope.launch {
            posts.addAll(repository.getPosts())
            postsLiveData.postValue(posts)
        }
    }


     fun getPost() {
        viewModelScope.launch {
            val post = repository.getPost(posts.size+1)
            posts.add(post)
            postsLiveData.postValue(posts)
        }
    }

}