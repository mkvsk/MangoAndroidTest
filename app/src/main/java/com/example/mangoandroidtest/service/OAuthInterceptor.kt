package com.example.mangoandroidtest.service

import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(
    private val tokenType: String,
    private val accessToken: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val builder = request.newBuilder()

        builder.addHeader("Authorization", "$tokenType $accessToken").build()
        return chain.proceed(builder.build())
    }

}