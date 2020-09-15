package com.example.Android_WebService.repository

import com.example.Android_WebService.repository.api.Post
import com.example.Android_WebService.repository.api.PostsApiService


class PostRepository {
    private val apiService = PostsApiService()

    suspend fun getPosts() = apiService.getPosts()

    suspend fun getPost(index : Int) = apiService.getPost(index)

    suspend fun postAPost(post: Post) = apiService.postAPost(post)
}