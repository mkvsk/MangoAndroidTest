package com.example.mangoandroidtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.service.request.RegisterRequest
import com.example.mangoandroidtest.service.response.RegisterResponse
import com.example.mangoandroidtest.ui.repository.RegisterRepository

class RegisterViewModel : ViewModel() {

    private val repository by lazy { RegisterRepository() }

    private val _phone = MutableLiveData("")
    val phone: LiveData<String> get() = _phone

    fun setPhone(value: String) {
        _phone.value = value
    }

    private val _name = MutableLiveData("")
    val name: LiveData<String> get() = _name

    fun setName(value: String) {
        _name.value = value
    }

    private val _username = MutableLiveData("")
    val username: LiveData<String> get() = _username

    fun setUsername(value: String) {
        _username.value = value
    }

    fun register(callback: ResultCallback<RegisterResponse>) {
        val request = RegisterRequest(phone.value!!, name.value!!, username.value!!)
        repository.register(request, object : ResultCallback<RegisterResponse> {
            override fun onResult(value: RegisterResponse?) {
                callback.onResult(value)
            }

            override fun onFailure(value: RegisterResponse?) {
                callback.onFailure(value)
            }

        })
    }
}