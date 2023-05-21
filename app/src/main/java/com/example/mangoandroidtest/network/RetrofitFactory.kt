package com.example.mangoandroidtest.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import online.example.mangoandroidtest.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private var ACCESS_TOKEN: String = ""
    var REFRESH_TOKEN: String = ""
    private val gson = GsonBuilder().setLenient().create()
    private const val URL = BuildConfig.API_URL
    private var client =
        OkHttpClient.Builder().addInterceptor(OAuthInterceptor("Bearer", ACCESS_TOKEN)).build()

    fun apiService(): NetworkInfoService {
        return Retrofit.Builder().baseUrl(URL).client(client)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            ).build().create(NetworkInfoService::class.java)
    }

    fun updateAccessJWT(token: String) {
        ACCESS_TOKEN = token
        client =
            OkHttpClient.Builder().addInterceptor(OAuthInterceptor("Bearer", ACCESS_TOKEN)).build()
    }

    fun updateRefreshJWT(token: String) {
        REFRESH_TOKEN = token
    }

}