package com.example.Android_WebService.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Android_WebService.model.User
import com.example.Android_WebService.repository.Repository

class loginViewModel: ViewModel() {
    private val users = MutableLiveData<List<User>>()
    private var userList = mutableListOf<User>()
    private val Repository = Repository()

    init {
        userList.add(User(Repository.getUserName(), Repository.getPassword()))
        users.value = userList
    }

    fun signIn(username: String, pass: String): Boolean {
        return getUsername().equals(username) && getUserPassword().equals(pass)
    }

    fun addUser(username: String, pass: String){
        val user: User = User (username,pass)
        Repository.addUser(user)
        userList.add(user)
        users.value = userList
    }

    fun getUsername() = userList[0].username
    fun getUserPassword() = userList[0].pass
}