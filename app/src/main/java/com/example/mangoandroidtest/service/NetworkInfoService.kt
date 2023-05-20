package com.example.mangoandroidtest.service

import com.example.mangoandroidtest.service.request.RegisterRequest
import com.example.mangoandroidtest.service.request.SendAuthCodeRequest
import com.example.mangoandroidtest.service.response.CheckAuthCodeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkInfoService {

    @POST("/api/v1/users/send-auth-code/")
    fun sendAuthCode(@Query(value = "phone") phone: String): Call<SendAuthCodeRequest>

    @POST("/api/v1/users/check-auth-code/")
    fun checkAuthCode(
        @Query(value = "phone") phone: String,
        @Query(value = "code") code: String
    ): Call<CheckAuthCodeResponse>

    @POST("/api/v1/users/register/")
    fun userRegister(
       @Body request: RegisterRequest
    )

//    @GET("/api/v1/users/me")
//    fun getCurrentUser(
//    )
}