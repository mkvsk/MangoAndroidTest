package com.example.mangoandroidtest.ui.repository

import android.util.Log
import com.example.mangoandroidtest.callback.ResultCallback
import com.example.mangoandroidtest.callback.TokenRefreshCallback
import com.example.mangoandroidtest.network.RetrofitFactory
import com.example.mangoandroidtest.network.request.RefreshTokenRequest
import com.example.mangoandroidtest.network.request.UserUpdateRequest
import com.example.mangoandroidtest.network.response.GetCurrentUserResponse
import com.example.mangoandroidtest.network.response.RefreshTokenResponse
import com.example.mangoandroidtest.network.response.UserUpdateResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class UserRepository {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    companion object {
        const val TAG = "UserRepository"
    }

    fun getCurrentUser(callback: ResultCallback<GetCurrentUserResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().getCurrentUser()
                .enqueue(object : Callback<GetCurrentUserResponse> {
                    override fun onResponse(
                        call: Call<GetCurrentUserResponse>,
                        response: Response<GetCurrentUserResponse>
                    ) {
                        when (response.code()) {
                            200, 201 -> {
                                Log.d(TAG, "GET CURRENT USER OK ${response.body().toString()}")
                                callback.onResult(response.body())
                            }
                            401 -> {
                                Log.d(
                                    TAG,
                                    "GET CURRENT USER TOKEN EXPIRED ${response.body().toString()}"
                                )
                                callback.onTokenExpired()
                            }
                            else -> {
                                Log.d(
                                    TAG,
                                    "GET CURRENT USER CODE ERROR ${response.body().toString()}"
                                )
                                callback.onFailure(null)
                            }
                        }

                    }

                    override fun onFailure(call: Call<GetCurrentUserResponse>, t: Throwable) {
                        Log.d(TAG, "GET CURRENT CODE EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }

    fun refreshToken(callback: TokenRefreshCallback<RefreshTokenResponse>) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService()
                .refreshToken(RefreshTokenRequest(RetrofitFactory.REFRESH_TOKEN))
                .enqueue(object : Callback<RefreshTokenResponse> {
                    override fun onResponse(
                        call: Call<RefreshTokenResponse>,
                        response: Response<RefreshTokenResponse>
                    ) {
                        if (response.code() == 200) {
                            Log.d(TAG, "REFRESH TOKEN OK")
                            callback.onResult(response.body())
                        } else {
                            Log.d(TAG, "REFRESH TOKEN ERROR")
                            callback.onFailure(null)
                        }
                    }

                    override fun onFailure(call: Call<RefreshTokenResponse>, t: Throwable) {
                        Log.d(TAG, "REFRESH TOKEN EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }

    fun updateCurrentUser(
        request: UserUpdateRequest,
        callback: ResultCallback<UserUpdateResponse>
    ) {
        scope.launch(Dispatchers.IO) {
            RetrofitFactory.apiService().updateCurrentUser(request)
                .enqueue(object : Callback<UserUpdateResponse> {
                    override fun onResponse(
                        call: Call<UserUpdateResponse>,
                        response: Response<UserUpdateResponse>
                    ) {
                        when (response.code()) {
                            200, 201 -> {
                                Log.d(TAG, "UPDATE CURRENT USER OK ${response.body().toString()}")
                                callback.onResult(response.body())
                            }
                            401 -> {
                                Log.d(
                                    TAG,
                                    "UPDATE CURRENT USER TOKEN EXPIRED ${
                                        response.body().toString()
                                    }"
                                )
                                callback.onTokenExpired()
                            }
                            else -> {
                                Log.d(
                                    TAG,
                                    "UPDATE CURRENT USER CODE ERROR ${response.body().toString()}"
                                )
                                callback.onFailure(null)
                            }
                        }

                    }

                    override fun onFailure(call: Call<UserUpdateResponse>, t: Throwable) {
                        Log.d(TAG, "UPDATE CURRENT USER EXCEPTION")
                        t.printStackTrace()
                        callback.onFailure(null)
                    }

                })
        }
    }


}
