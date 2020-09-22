package com.example.Android_WebService.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Android_WebService.model.User
import com.example.Android_WebService.repository.LoginRepository
import com.example.Android_WebService.repository.Repository
import com.example.Android_WebService.sharedPreference.Provider.Companion.getPassword
import com.example.Android_WebService.sharedPreference.Provider.Companion.getUserName

class loginViewModel: ViewModel() {

    var userLiveData = MutableLiveData<User>()

    private val repository = LoginRepository()

    fun signIn(email: String, clave: String, usuario : String) =
        repository.signIn(User(email, clave, usuario, usuario,"",""))

    fun signUp(email: String, clave: String, usuario : String) =
        repository.signUp(User(email, clave, usuario, usuario,"",""))

    fun getUser() = userLiveData

    fun signInSP(username: String, pass: String): Boolean {
        return getUsername().equals(username) && getUserPassword().equals(pass)
    }
    fun signUpSP(user: User) {
        repository.addUser(user)
        userLiveData.value = user
    }

    fun getUsername() = repository.getUserName()
    fun getUserPassword() = repository.getPassword()
    fun setToken(token: String) = repository.setToken(token)
    /*
    private val users = MutableLiveData<List<User>>()
    private var userList = mutableListOf<User>()
    private val Repository = Repository()

    init {
        userList.add(User(Repository.getUserName(), Repository.getPassword()))
        users.value = userList
    }



    fun addUser(username: String, pass: String){
        val user: User = User (username,pass)
        Repository.addUser(user)
        userList.add(user)
        users.value = userList
    }

    fun getUsername() = Repository.getUserName()
    fun getUserPassword() = Repository.getPassword()
    fun getToken() = Repository.getToken()*/
}