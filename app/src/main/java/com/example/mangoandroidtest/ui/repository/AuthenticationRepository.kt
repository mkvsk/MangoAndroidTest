package com.example.mangoandroidtest.ui.repository

import android.util.Log
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.network.RetrofitFactory
import com.example.mangoandroidtest.network.request.CheckAuthCodeRequest
import com.example.mangoandroidtest.network.request.RegisterRequest
import com.example.mangoandroidtest.network.request.SendAuthCodeRequest
import com.example.mangoandroidtest.network.response.CheckAuthCodeResponse
import com.example.mangoandroidtest.network.response.RegisterResponse
import com.example.mangoandroidtest.network.response.SendAuthCodeResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class AuthenticationRepository {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    companion object {
        const val TAG = "AuthenticationRepository"
    }

    fun sendAuthCode(request: SendAuthCodeRequest, callback: ResultCallback<SendAuthCodeResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().sendAuthCode(request)
                .enqueue(object : Callback<SendAuthCodeResponse> {
                    override fun onResponse(
                        call: Call<SendAuthCodeResponse>,
                        response: Response<SendAuthCodeResponse>
                    ) {
                        if (response.code() == 201 || response.code() == 200) {
                            Log.d(TAG, "SEND AUTH CODE OK ${response.body().toString()}")
                            callback.onResult(response.body())
                        } else {
                            Log.d(TAG, "SEND AUTH CODE ERROR ${response.body().toString()}")
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<SendAuthCodeResponse>, t: Throwable) {
                        Log.d(TAG, "SEND AUTH CODE EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }

    fun checkAuthCode(
        request: CheckAuthCodeRequest,
        callback: ResultCallback<CheckAuthCodeResponse>
    ) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().checkAuthCode(request)
                .enqueue(object : Callback<CheckAuthCodeResponse> {
                    override fun onResponse(
                        call: Call<CheckAuthCodeResponse>,
                        response: Response<CheckAuthCodeResponse>
                    ) {
                        if (response.code() == 201 || response.code() == 200) {
                            Log.d(TAG, "CHECK AUTH CODE OK ${response.body().toString()}")
                            callback.onResult(response.body())
                        } else {
                            Log.d(TAG, "CHECK AUTH CODE ERROR ${response.body().toString()}")
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<CheckAuthCodeResponse>, t: Throwable) {
                        Log.d(TAG, "CHECK AUTH CODE EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }

    fun register(request: RegisterRequest, callback: ResultCallback<RegisterResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().userRegister(request)
                .enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.code() == 201 || response.code() == 200) {
                            Log.d(TAG, "REGISTER CODE OK ${response.body().toString()}")
                            callback.onResult(response.body())
                        } else {
                            Log.d(
                                TAG,
                                "onResponse: REGISTER CODE ERROR ${response.body().toString()}"
                            )
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Log.d(TAG, "REGISTER CODE EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }
}