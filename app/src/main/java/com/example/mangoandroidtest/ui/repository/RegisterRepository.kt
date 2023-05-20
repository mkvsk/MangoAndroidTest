package com.example.mangoandroidtest.ui.repository

import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.service.request.RegisterRequest
import com.example.mangoandroidtest.service.response.RegisterResponse

class RegisterRepository {
    fun register(request: RegisterRequest, callback: ResultCallback<RegisterResponse>) {
        val response = RegisterResponse("", "", 1337)
        callback.onResult(response)
    }

}
