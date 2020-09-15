package com.example.Android_WebService.repository

import com.example.Android_WebService.model.User
import com.example.Android_WebService.sharedPreference.Provider

class Repository {

    private val sharedPreferences = Provider

    fun addUser(user: User) {
        sharedPreferences.setUserPass(user.username, user.pass)
    }

    fun getUserName() = sharedPreferences.getUserName()
    fun getPassword() = sharedPreferences.getPassword()
}