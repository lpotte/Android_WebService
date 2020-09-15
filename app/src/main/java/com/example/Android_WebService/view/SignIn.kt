package com.example.Android_WebService.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.Android_WebService.R
import com.example.Android_WebService.viewmodel.loginViewModel

class SignIn : Fragment() {
    lateinit var navController: NavController
    val loginViewModel: loginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.sign_in).setOnClickListener {
            looged()
        }

        view.findViewById<Button>(R.id.sign_up).setOnClickListener {
            navController.navigate(R.id.action_signIn_to_signUp)
        }
    }

    private fun looged() {
        var user = requireView().findViewById<EditText>(R.id.username).text.toString()
        var pass = requireView().findViewById<EditText>(R.id.pass).text.toString()

        if(loginViewModel.signIn(user, pass)){
            navController.navigate(R.id.action_signIn_to_home2)
        }
        //requireView().findViewById<TextView>(R.id.textView2).text = loginViewModel.getUserPassword().toString()
    }
}