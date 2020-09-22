package com.example.Android_WebService.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.Android_WebService.R
import com.example.Android_WebService.model.User
import com.example.Android_WebService.viewmodel.loginViewModel

class SignUp : Fragment() {
    lateinit var navController: NavController
    val loginViewModel: loginViewModel by viewModels()
    var theToken : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.submit ).setOnClickListener {
            val email : String =  "molinares@correo.com"
            var user = requireView().findViewById<EditText>(R.id.username).text.toString()
            var pass = requireView().findViewById<EditText>(R.id.pass).text.toString()

            loginViewModel.signUp(email,pass, user).observe(getViewLifecycleOwner(), Observer { user :User ->

                Log.d("MyOut", "Fragment  signUp " + user + " error " + user.error)
                theToken = user.token
                loginViewModel.signUpSP(user)

            })
            navController.navigate(R.id.action_signUp_to_signIn)
        }
    }
}