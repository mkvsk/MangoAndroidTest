package com.example.mangoandroidtest.ui.repository

import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.service.request.CheckAuthCodeRequest
import com.example.mangoandroidtest.service.request.SendAuthCodeRequest
import com.example.mangoandroidtest.service.response.CheckAuthCodeResponse
import com.example.mangoandroidtest.service.response.SendAuthCodeResponse

class AuthenticationRepository {
    fun sendAuthCode(request: SendAuthCodeRequest, callback: ResultCallback<SendAuthCodeResponse>) {
        val response = SendAuthCodeResponse(true)
        callback.onResult(response)
    }

    fun checkAuthCode(
        request: CheckAuthCodeRequest,
        callback: ResultCallback<CheckAuthCodeResponse>
    ) {
        val response = CheckAuthCodeResponse("", "", null, false)
        callback.onResult(response)
    }
}