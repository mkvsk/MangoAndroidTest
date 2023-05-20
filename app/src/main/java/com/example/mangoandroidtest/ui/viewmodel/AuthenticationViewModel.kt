package com.example.mangoandroidtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.service.request.CheckAuthCodeRequest
import com.example.mangoandroidtest.service.request.SendAuthCodeRequest
import com.example.mangoandroidtest.service.response.CheckAuthCodeResponse
import com.example.mangoandroidtest.service.response.SendAuthCodeResponse
import com.example.mangoandroidtest.ui.repository.AuthenticationRepository

class AuthenticationViewModel : ViewModel() {

    private val repository by lazy { AuthenticationRepository() }

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

    private val _isUserAuthenticated = MutableLiveData(false)
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    fun setIsUserAuthenticated(value: Boolean) {
        _isUserAuthenticated.value = value
    }

    fun sendAuthCode(callback: ResultCallback<SendAuthCodeResponse>) {
        val request = SendAuthCodeRequest(phone.value!!)
        repository.sendAuthCode(request, object : ResultCallback<SendAuthCodeResponse> {
            override fun onResult(value: SendAuthCodeResponse?) {
                callback.onResult(value)
            }

            override fun onFailure(value: SendAuthCodeResponse?) {
                callback.onFailure(value)
            }

        })
    }

    fun checkVCode(authCode: String, callback: ResultCallback<CheckAuthCodeResponse>) {
        val request = CheckAuthCodeRequest(phone.value.toString(), authCode)
        repository.checkAuthCode(request, object : ResultCallback<CheckAuthCodeResponse> {
            override fun onResult(value: CheckAuthCodeResponse?) {
                callback.onResult(value)
            }

            override fun onFailure(value: CheckAuthCodeResponse?) {
                callback.onFailure(value)
            }

        })
    }

}