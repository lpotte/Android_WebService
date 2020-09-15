package com.example.Android_WebService.repository.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

//A suspending function is simply a function that can be paused and resumed at a later time
interface PostsApi {
    @GET("todos/")
    suspend fun getPosts(): List<Post>

    @GET("todos/{index}/")
    suspend fun getPost(@Path("index") index: Int): Post

    @POST("todos/")
    suspend fun postAPost(@Body post: Post): Post
}
