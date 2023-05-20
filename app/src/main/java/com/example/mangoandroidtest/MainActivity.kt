package com.example.mangoandroidtest

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mangoandroidtest.ui.viewmodel.AuthenticationViewModel
import com.example.mangoandroidtest.ui.viewmodel.RegisterViewModel
import com.example.mangoandroidtest.ui.viewmodel.UserViewModel
import online.example.mangoandroidtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthenticationViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instantiateViewModels()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun instantiateViewModels() {
        authViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}