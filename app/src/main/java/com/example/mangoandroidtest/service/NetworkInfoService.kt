package com.example.mangoandroidtest.service

import com.example.mangoandroidtest.service.request.*
import com.example.mangoandroidtest.service.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface NetworkInfoService {

    @POST("/api/v1/users/send-auth-code/")
    fun sendAuthCode(@Body request: SendAuthCodeRequest): Call<SendAuthCodeResponse>

    @POST("/api/v1/users/check-auth-code/")
    fun checkAuthCode(
        @Body request: CheckAuthCodeRequest
    ): Call<CheckAuthCodeResponse>

    @POST("/api/v1/users/register/")
    fun userRegister(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>

    @GET("/api/v1/users/me/")
    fun getCurrentUser(): Call<GetCurrentUserResponse>

    @PUT("/api/v1/users/me/")
    fun updateCurrentUser(@Body request: UserUpdateRequest): Call<UserUpdateResponse>

    @POST("/api/v1/users/refresh-token/")
    fun refreshToken(@Body request: RefreshTokenRequest): Call<RefreshTokenResponse>


}